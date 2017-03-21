package com.Qlmain;

import com.Qlmain.QL.*;
//import com.Qlmain.types_Of_Expr.Type;
import com.Qlmain.type_check.Type_Checking;
import com.Qlmain.exceptions.UndefinedException;
import com.Qlmain.types_Of_Expr.types.Type;
import com.Qlmain.types_Of_Expr.types.Type_bool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by matt on 21/02/2017.
 */
public class MainFuncTest {
    @Test
    public void main() throws Exception {

    }

    @Test
    public void buildQLAST() throws Exception {

    }

    private Form formToTest;

    @Before
    public void setUp() throws Exception {

        String testInput = "form taxOfficeExample { \n" +
                "  \"Did you sell a house in 2010?\"\n" +
                "    hasSoldHouse: boolean\n" +
                "  \"Did you buy a house in 2010?\"\n" +
                "    hasBoughtHouse: boolean\n" +
                "  \"Did you enter a loan?\"\n" +
                "    hasMaintLoan: boolean\n" +
                "\n" +
                "  if (hasSoldHouse AND hasBoughtHouse OR true) {\n" +
                "    \"What was the selling price?\"\n" +
                "      sellingPrice: money\n" +
                "    \"Private debts for the sold house:\"\n" +
                "      privateDebt: money\n" +
                "    \"Value residue:\"\n" +
                "      valueResidue: money = \n" +
                "        (sellingPrice - 11)\n" +
                "  }\n" +
                "\n" +
                "}";

        MainFunc mainFunc = new MainFunc();

        formToTest = mainFunc.buildQLAST(testInput);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void checkFormID() {
        assertEquals("taxOfficeExample", formToTest.getName());
    }

    @Test
    public void checkOpenAndReadFile() throws InvocationTargetException, InterruptedException {
        assertEquals("", new OpenAndReadTheQl().QlRead());
    }

    @Test
    public void checkFormQuestionsCount() {
        assertEquals(4, formToTest.getStatementList().size());
    }

    @Test
    public void checkFormIfStatementsCount() {
        //assertEquals(1, formToTest.getIfStatementList().size());
    }

    @Test
    public void checkFormAddQuestion() {
        formToTest.addStatement(new Question(null, null, null, 0));

        assertEquals(5, formToTest.getStatementList().size());

    }

    @Test
    public void checkFormAddIfStatement()
    {
        Map<String,Type> variablesAndTypes = Type_Checking.getVariablesAndTypes();
        for (Statement qu : formToTest.getStatementList()){
            if (qu instanceof Question) {
                try {
                    assertEquals(variablesAndTypes.get(((Question) qu).name), ((Question) qu).type.exprTypeChecker());
                } catch (UndefinedException e) {
                    System.out.println("Undefined variable");
                }
            }
        }
        //formToTest.addIfStatement(new IfStatement());

        //assertEquals(2, formToTest.getIfStatementList().size());
    }

    @Test
    public void checkifStatementEval()
    {
        for (Statement st : formToTest.getStatementList()){
            if (st instanceof IfStatement) {
                try {
                    assertEquals(new Type_bool(), ((IfStatement) st).getIfCase().exprTypeChecker());

                } catch (UndefinedException e) {
                    System.out.println("Undefined variable");
                }
            }
        }
        //formToTest.addIfStatement(new IfStatement());

        //assertEquals(2, formToTest.getIfStatementList().size());
    }

}