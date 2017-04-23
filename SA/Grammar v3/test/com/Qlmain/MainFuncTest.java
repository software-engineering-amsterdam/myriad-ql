package com.Qlmain;

import com.Qlmain.QL.*;
import com.Qlmain.typeCheck.TypeChecking;
import com.Qlmain.typesOfExpr.types.Type;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by matt on 21/02/2017.
 */
public class MainFuncTest {

    private Form wrongFormToTest;

    @Before
    public void setUp() throws Exception {

        Example_inputs inputs = new Example_inputs();
        MainFunc mainFunc = new MainFunc();

        wrongFormToTest = mainFunc.buildQLAST(inputs.getWrongTestInput());
    }

    @Test
    public void checkFormID() {
        assertEquals("taxOfficeExample", wrongFormToTest.getName());
    }

    @Test
    public void qlWithErrors() throws InvocationTargetException, InterruptedException {
        TypeChecking typeCheck = new TypeChecking();
        assertEquals(false, typeCheck.TypeCheckingMethod(wrongFormToTest));
        checkIfStatementEval(typeCheck);
    }

    @Test
    public void checkFormQuestionsCount() {
        assertEquals(4, wrongFormToTest.getStatementList().size());
    }

    @Test
    public void checkFormIfStatementsCount() {
        //Counting number of ifs for the wrong form
        List<Statement> temp = wrongFormToTest.getStatementList();
        int ifStatementCount = count(temp);
        assertEquals(2, ifStatementCount);
    }

    private int count (List<Statement> temp) {
        int ifStatementCount =0;
        for (Statement state : temp) {
            if (state instanceof IfStatement) {
                ifStatementCount = ifStatementCount + 1 + count( ((IfStatement) state).getStatementsList() );
            }
        }
        return ifStatementCount;
    }


    @Test
    public void checkFormAddQuestion() {
        wrongFormToTest.addStatement(new Question(null, null, null, 0));
        assertEquals(5, wrongFormToTest.getStatementList().size());

    }

    @Test
    public void checkVariablesAndTypesTable()
    {
        TypeChecking typeCheck = new TypeChecking();
        assertEquals(false, typeCheck.TypeCheckingMethod(wrongFormToTest) );
        Map<String,Type> variablesAndTypes = typeCheck.getVariablesAndTypes();
        checkVariablesAndTypesTableIteration(wrongFormToTest.getStatementList(), variablesAndTypes, typeCheck);
    }

    private void checkVariablesAndTypesTableIteration(List<Statement> temp, Map<String,Type> variablesAndTypes, TypeChecking typeCheck) {
        Type ty;
        for (Statement qu : temp) {
            if (qu instanceof Question) {
                ty = ((Question) qu).type.exprTypeChecker(typeCheck);
                if (!ty.checkNoType() && !ty.checkWrongType())
                    assertEquals( variablesAndTypes.get(((Question) qu).name) , ty );
            }else if (qu instanceof IfStatement){
                checkVariablesAndTypesTableIteration( ((IfStatement) qu).getStatementsList(), variablesAndTypes, typeCheck );
            }
        }
    }


    private void checkIfStatementEval(TypeChecking typeCheck) {
        testIf(wrongFormToTest.getStatementList(), typeCheck);
    }

    private void testIf(List<Statement> statementLi, TypeChecking typeCheck) {
        for (Statement st : statementLi){
            if (st instanceof IfStatement) {
                assertEquals(true, ((IfStatement) st).getIfCase().exprTypeChecker(typeCheck).checkBoolType());

                for (Statement st2 : ((IfStatement) st).getStatementsList()){
                    if (st2 instanceof IfStatement) {
                        assertEquals(true, ((IfStatement) st2).getIfCase().exprTypeChecker(typeCheck).checkNoType());
                    }
                }
            }
        }
    }

}