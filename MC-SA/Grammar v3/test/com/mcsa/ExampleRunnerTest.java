package com.mcsa;

import com.mcsa.QL.Form;
import com.mcsa.QL.IfStatement;
import com.mcsa.QL.Question;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by matt on 21/02/2017.
 */
public class ExampleRunnerTest {

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
                "  if (hasSoldHouse) {\n" +
                "    \"What was the selling price?\"\n" +
                "      sellingPrice: money\n" +
                "    \"Private debts for the sold house:\"\n" +
                "      privateDebt: money\n" +
                "    \"Value residue:\"\n" +
                "      valueResidue: money = \n" +
                "        (sellingPrice - privateDebt)\n" +
                "  }\n" +
                "\n" +
                "}";

        ExampleRunner exampleRunner = new ExampleRunner();

        formToTest = exampleRunner.buildQLAST(testInput);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void checkFormID() {
        assertEquals("taxOfficeExample", formToTest.getName());
    }

    @Test
    public void checkFormQuestionsCount() {
        assertEquals(3, formToTest.getQuestionList().size());
    }

    @Test
    public void checkFormIfStatementsCount() {
        assertEquals(1, formToTest.getIfStatementList().size());
    }

    @Test
    public void checkFormAddQuestion() {
        formToTest.addQuestion(new Question(null, null, null));

        assertEquals(4, formToTest.getQuestionList().size());

    }

    @Test
    public void checkFormAddIfStatement()
    {
        formToTest.addIfStatement(new IfStatement(null));

        assertEquals(2, formToTest.getIfStatementList().size());
    }

}