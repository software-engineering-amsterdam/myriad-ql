package com.Qlmain;

import com.Qlmain.QL.*;
import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.typeCheck.TypeChecking;
import com.Qlmain.typesOfExpr.types.Type;
import com.Qlmain.typesOfExpr.types.TypeBool;
import com.Qlmain.typesOfExpr.types.TypeMon;
import com.Qlmain.typesOfExpr.types.TypeStr;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

/**
 * Created by matt on 21/02/2017.
 */
public class MainTestCorrectForm {

    private Map<String,Type> variableTypeTest = new HashMap<>();
    private Map<String,Object> variableAndValuesTest = new HashMap<>();

    private Form correctFormToTest;

    @Before
    public void setUp() throws Exception {
        variableTypeTest.put("hasSoldHouse",new TypeBool());
        variableTypeTest.put("hasBoughtHouse",new TypeBool());
        variableTypeTest.put("hasMainLoan",new TypeStr());
        variableTypeTest.put("sellingPrice",new TypeMon());
        variableTypeTest.put("privateDebt",new TypeMon());
        variableTypeTest.put("valueResidue",new TypeMon());
        variableTypeTest.put("sellingPrices",new TypeMon());
        variableTypeTest.put("privateDebts",new TypeMon());
        variableTypeTest.put("valueResidues",new TypeMon());

        variableAndValuesTest.put("hasSoldHouse",false);
        variableAndValuesTest.put("hasBoughtHouse",false);
        variableAndValuesTest.put("hasMainLoan","");
        variableAndValuesTest.put("sellingPrice",0.0);
        variableAndValuesTest.put("privateDebt",0.0);
        variableAndValuesTest.put("valueResidue",-11.0);
        variableAndValuesTest.put("sellingPrices",0.0);
        variableAndValuesTest.put("privateDebts",0.0);
        variableAndValuesTest.put("valueResidues",-89.0);

        Example_inputs inputs = new Example_inputs();
        MainFunc mainFunc = new MainFunc();

        correctFormToTest = mainFunc.buildQLAST(inputs.getCorrectTestInput());

    }

    @Test
    public void checkFormID() {
        assertEquals("taxOfficeExample", correctFormToTest.getName());
    }

    @Test
    public void qlWithoutErrors() throws InvocationTargetException, InterruptedException {
        TypeChecking typeCheck = new TypeChecking();
        typeCheck.TypeCheckingMethod(correctFormToTest);
        checkIfStatementEval(typeCheck);
        checkEvaluation();
    }

    @Test
    public void checkFormQuestionsCount() {
        assertEquals(5, correctFormToTest.getStatementList().size());
    }

    @Test
    public void checkFormIfStatementsCount() {
        //Counting number of ifs for the correct form
        List<Statement> temp = correctFormToTest.getStatementList();
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
        correctFormToTest.addStatement(new Question(null, null, null, 0));
        assertEquals(6, correctFormToTest.getStatementList().size());
    }

    @Test
    public void checkQuestionType()
    {
        TypeChecking typeCheck = new TypeChecking();
        assertEquals(true, typeCheck.TypeCheckingMethod(correctFormToTest) );
        checkQuestionTypeIteration(correctFormToTest.getStatementList(), typeCheck);
    }

    private void checkQuestionTypeIteration(List<Statement> temp, TypeChecking typeCheck) {
        Type ty;
        for (Statement qu : temp) {
            if (qu instanceof Question) {
                ty = ((Question) qu).type.exprTypeChecker(typeCheck);
                if (!ty.checkNoType() && !ty.checkWrongType()) {
                    assertEquals(variableTypeTest.get(((Question) qu).name).checkBoolType(), ty.checkBoolType());
                    assertEquals(variableTypeTest.get(((Question) qu).name).checkIntType(), ty.checkIntType());
                    assertEquals(variableTypeTest.get(((Question) qu).name).checkMonType(), ty.checkMonType());
                    assertEquals(variableTypeTest.get(((Question) qu).name).checkStrType(), ty.checkStrType());
                }
            }else if (qu instanceof IfStatement){
                checkQuestionTypeIteration( ((IfStatement) qu).getStatementsList(), typeCheck );
            }
        }
    }


    private void checkIfStatementEval(TypeChecking typeCheck) {
        testIf(correctFormToTest.getStatementList(), typeCheck);
    }

    private void testIf(List<Statement> statementLi, TypeChecking typeCheck) {
        int count =0;
        for (Statement st : statementLi){
            if (st instanceof IfStatement) {
                if (count == 0) {
                    assertEquals(true, ((IfStatement) st).getIfCase().exprTypeChecker(typeCheck).checkBoolType());
                    count++;
                } else if (count > 0)
                    assertEquals(true, ((IfStatement) st).getIfCase().exprTypeChecker(typeCheck).checkBoolType());
            }
        }
    }

    private void checkEvaluation() {
        iterativeEvaluation(correctFormToTest.getStatementList());
    }

    private void iterativeEvaluation(List<Statement> statementLi) {

            Evaluation eval = new Evaluation();
            Map<String, Object> variablesAndValues = eval.evaluateAST(statementLi);

            for (Map.Entry<String, Object> entry : variablesAndValues.entrySet()) {
                assertEquals(entry.getValue(), variableAndValuesTest.get(entry.getKey()));
            }

    }

}
