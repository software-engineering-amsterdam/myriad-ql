package test.org.uva.taxfree.ql.semantics;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.node.expression.BinaryExpressionNode;
import org.uva.taxfree.ql.model.node.literal.IntegerLiteralNode;
import org.uva.taxfree.ql.model.operators.GreaterEqualOperator;
import org.uva.taxfree.ql.model.operators.Operator;
import test.org.uva.taxfree.ql.SemanticsTester;

public class CalculationTest extends SemanticsTester {

    @Test
    public void testGreaterEqualOperator() throws Exception {
        assertTrue(5, new GreaterEqualOperator(), 5);
        assertTrue(6, new GreaterEqualOperator(), 5);
        assertFalse(5, new GreaterEqualOperator(), 6);
    }

    private void assertFalse(int leftHand, Operator operator, int rightHand) {
        assertThat(leftHand, operator, rightHand, "false");
    }

    private void assertTrue(int leftHand, Operator operator, int rightHand) {
        assertThat(leftHand, operator, rightHand, "true");
    }

    private void assertThat(int leftHand, Operator operator, int rightHand, String expectedResult) {
        BinaryExpressionNode expression = new BinaryExpressionNode(
                new IntegerLiteralNode(leftHand, emptySource()),
                operator,
                new IntegerLiteralNode(rightHand, emptySource()),
                emptySource());
        Assert.assertEquals(expression.evaluate(), expectedResult);
    }


    private SourceInfo emptySource() {
        return new SourceInfo(0, 0, 0, 0);
    }

    @Override
    protected String fileDirectory() {
        return "sampleCalculations";
    }
}
