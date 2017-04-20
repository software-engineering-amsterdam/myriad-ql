package com.Qlmain;

import com.Qlmain.QL.*;
import com.Qlmain.error_types.Error_codes_list;
import com.Qlmain.type_check.TypeChecking;
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
        checkErrorCodes();
        checkIfStatementEval();
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
        Map<String,Type> variablesAndTypes = TypeChecking.getVariablesAndTypes();
        checkVariablesAndTypesTableIteration(wrongFormToTest.getStatementList(), variablesAndTypes);
    }

    private void checkVariablesAndTypesTableIteration(List<Statement> temp, Map<String,Type> variablesAndTypes) {
        Type ty;
        for (Statement qu : temp) {
            if (qu instanceof Question) {
                ty = ((Question) qu).type.exprTypeChecker();
                if (!ty.checkNoType() && !ty.checkWrongType())
                    assertEquals( variablesAndTypes.get(((Question) qu).name) , ty );
            }else if (qu instanceof IfStatement){
                checkVariablesAndTypesTableIteration( ((IfStatement) qu).getStatementsList(), variablesAndTypes );
            }
        }
    }

    private void checkErrorCodes() {
        assertEquals(3, Error_codes_list.get_error_list().size());
    }

    private void checkIfStatementEval() {
        testIf(wrongFormToTest.getStatementList());
    }

    private void testIf(List<Statement> statementLi) {
        for (Statement st : statementLi){
            if (st instanceof IfStatement) {
                assertEquals(true, ((IfStatement) st).getIfCase().exprTypeChecker().checkBoolType());

                for (Statement st2 : ((IfStatement) st).getStatementsList()){
                    if (st2 instanceof IfStatement) {
                        assertEquals(true, ((IfStatement) st2).getIfCase().exprTypeChecker().checkNoType());
                    }
                }
            }
        }
    }

}