package com.Qlmain;

import com.Qlmain.QL.*;
import com.Qlmain.error_types.Error_codes_list;
import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.typesOfExpr.types.Type;
import com.Qlmain.typesOfExpr.types.Type_bool;
import com.Qlmain.typesOfExpr.types.Type_mon;
import com.Qlmain.typesOfExpr.types.Type_str;
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
        variableTypeTest.put("hasSoldHouse",new Type_bool());
        variableTypeTest.put("hasBoughtHouse",new Type_bool());
        variableTypeTest.put("hasMainLoan",new Type_str());
        variableTypeTest.put("sellingPrice",new Type_mon());
        variableTypeTest.put("privateDebt",new Type_mon());
        variableTypeTest.put("valueResidue",new Type_mon());
        variableTypeTest.put("sellingPrices",new Type_mon());
        variableTypeTest.put("privateDebts",new Type_mon());
        variableTypeTest.put("valueResidues",new Type_mon());

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
        checkErrorCodes();
        checkIfStatementEval();
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
        checkQuestionTypeIteration(correctFormToTest.getStatementList());
    }

    private void checkQuestionTypeIteration(List<Statement> temp) {
        Type ty;
        for (Statement qu : temp) {
            if (qu instanceof Question) {
                ty = ((Question) qu).type.exprTypeChecker();
                if (!ty.checkNoType() && !ty.checkWrongType()) {
                    assertEquals(variableTypeTest.get(((Question) qu).name).checkBoolType(), ty.checkBoolType());
                    assertEquals(variableTypeTest.get(((Question) qu).name).checkIntType(), ty.checkIntType());
                    assertEquals(variableTypeTest.get(((Question) qu).name).checkMonType(), ty.checkMonType());
                    assertEquals(variableTypeTest.get(((Question) qu).name).checkStrType(), ty.checkStrType());
                }
            }else if (qu instanceof IfStatement){
                checkQuestionTypeIteration( ((IfStatement) qu).getStatementsList() );
            }
        }
    }

    private void checkErrorCodes() {
        assertEquals(0, Error_codes_list.get_error_list().size() );
    }

    private void checkIfStatementEval() {
        testIf(correctFormToTest.getStatementList());
    }

    private void testIf(List<Statement> statementLi) {
        int count =0;
        for (Statement st : statementLi){
            if (st instanceof IfStatement) {
                if (count == 0) {
                    assertEquals(true, ((IfStatement) st).getIfCase().exprTypeChecker().checkBoolType());
                    count++;
                } else if (count > 0)
                    assertEquals(true, ((IfStatement) st).getIfCase().exprTypeChecker().checkBoolType());
            }
        }
    }

    private void checkEvaluation() {
        iterativeEvaluation(correctFormToTest.getStatementList());
    }

    private void iterativeEvaluation(List<Statement> statementLi) {

            Evaluation eval = new Evaluation();
            eval.initialise();
            Map<String, Object> variablesAndValues = eval.evaluateAST(statementLi);

            for (Map.Entry<String, Object> entry : variablesAndValues.entrySet()) {
                //System.out.println("------------"+variablesAndValues.get(entry.getKey()));
                assertEquals(entry.getValue(), variableAndValuesTest.get(entry.getKey()));
            }

    }

}
