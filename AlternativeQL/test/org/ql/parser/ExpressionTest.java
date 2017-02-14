package org.ql.parser;

import org.junit.Test;
import org.ql.ast.Form;
import org.ql.ast.expression.literal.Integer;
import org.ql.ast.expression.relational.Equals;
import org.ql.ast.statement.If;

import static org.junit.Assert.*;

public class ExpressionTest {
    @Test
    public void shouldCreateFormWithQuestionsInsideIf() {
        Parser parser = new Parser();
        String inputCode = "form ExampleForm {\n" +
                "    boolean hasSoldHouse: \"Did you sell a house in 2010?\";\n" +
                "    boolean hasBoughtHouse: \"Did you buy a house in 2010?\";\n" +
                "    boolean hasMainLoan:  \"Did you enter a loan?\";\n" +
                "\n" +
                "    if (hasSoldHouse) {\n" +
                "        money sellingPrice: \"What was the selling price?\";\n" +
                "        money privateDebt: \"Private debts for the sold house:\";\n" +
                "    }\n" +
                "}";
        int expectedIfStatementLocation = 3;
        int expectedAmountOfQuestionsInsideIfStatement = 2;

        Form ast = parser.parse(inputCode);
        If ifStatement = (If) ast.getStatement(expectedIfStatementLocation);
        assertSame(expectedAmountOfQuestionsInsideIfStatement, ifStatement.getStatements().size());
        assertEquals("hasSoldHouse", ifStatement.getCondition().toString());
    }

    @Test
    public void shouldCreateFormWithEqualsExpression() {
        Parser parser = new Parser();
        int expectedLeftHandValue = 5;
        int expectedRightHandValue = 7;
        String inputCode = "form ExampleForm {\n" +
                "    if (" + expectedLeftHandValue + "==" + expectedRightHandValue + ") {\n" +
                "        money sellingPrice: \"What was the selling price?\";\n" +
                "        money privateDebt: \"Private debts for the sold house:\";\n" +
                "    }\n" +
                "}";
        int expectedIfStatementLocation = 0;

        Form ast = parser.parse(inputCode);
        If ifStatement = (If) ast.getStatement(expectedIfStatementLocation);

        assert(ifStatement.getCondition() instanceof Equals);
        assert((((Equals) ifStatement.getCondition()).getLeft()) instanceof Integer);
        Integer leftHandValue = (Integer) (((Equals) ifStatement.getCondition()).getLeft());
        Integer rightHandValue = (Integer) (((Equals) ifStatement.getCondition()).getRight());
        assertSame(leftHandValue.getIntegerLiteral(), expectedLeftHandValue);
        assertSame(rightHandValue.getIntegerLiteral(), expectedRightHandValue);
    }
}
