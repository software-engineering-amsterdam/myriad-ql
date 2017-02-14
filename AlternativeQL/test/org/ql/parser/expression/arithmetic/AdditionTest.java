package org.ql.parser.expression.arithmetic;

import org.junit.Test;
import org.ql.ast.expression.arithmetic.Addition;
import org.ql.ast.expression.literal.Integer;
import org.ql.parser.Parser;

import static org.junit.Assert.*;

public class AdditionTest {
    @Test
    public void shouldParseAddition() {
        Parser parser = new Parser();
        int expectedLeftHandValue = 5;
        int expectedRightHandValue = 7;
        String inputCode = expectedLeftHandValue + "+" + expectedRightHandValue;

        Addition ast = (Addition) parser.parseExpression(inputCode);

        assertTrue(ast.getLeft() instanceof Integer);
        assertTrue(ast.getRight() instanceof Integer);

        Integer leftHandValue = (Integer) ast.getLeft();
        Integer rightHandValue = (Integer) ast.getRight();
        assertSame(leftHandValue.getIntegerLiteral(), expectedLeftHandValue);
        assertSame(rightHandValue.getIntegerLiteral(), expectedRightHandValue);
    }
}
