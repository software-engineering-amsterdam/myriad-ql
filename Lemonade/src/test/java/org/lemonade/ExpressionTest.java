package org.lemonade;

import org.junit.Test;
import org.lemonade.expression.BinaryExpression;

public class ExpressionTest {
    @Test
    public void testPrint() {
        BinaryExpression bin = new BinaryExpression(QLType.INTEGER, QLOperatorType.BinaryOperator.LT, 5, 6);
        System.out.println(bin);
    }
}
