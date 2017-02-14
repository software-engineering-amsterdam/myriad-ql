package org.ql.parser;

import org.junit.Test;
import org.ql.ast.Form;
import org.ql.ast.expression.literal.Integer;
import org.ql.ast.expression.relational.Equals;
import org.ql.ast.statement.If;

import static org.junit.Assert.*;

public class ExpressionTest {
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

        assertTrue(ifStatement.getCondition() instanceof Equals);
        assertTrue((((Equals) ifStatement.getCondition()).getLeft()) instanceof Integer);

        Integer leftHandValue = (Integer) (((Equals) ifStatement.getCondition()).getLeft());
        Integer rightHandValue = (Integer) (((Equals) ifStatement.getCondition()).getRight());
        assertSame(leftHandValue.getIntegerLiteral(), expectedLeftHandValue);
        assertSame(rightHandValue.getIntegerLiteral(), expectedRightHandValue);
    }
}
