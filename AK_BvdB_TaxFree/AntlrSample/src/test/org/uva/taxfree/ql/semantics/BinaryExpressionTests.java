package test.org.uva.taxfree.ql.semantics;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.node.expression.BinaryExpressionNode;
import org.uva.taxfree.ql.model.node.literal.BooleanLiteralNode;
import org.uva.taxfree.ql.model.node.literal.IntegerLiteralNode;
import org.uva.taxfree.ql.model.node.literal.LiteralNode;
import org.uva.taxfree.ql.model.node.literal.StringLiteralNode;
import org.uva.taxfree.ql.model.operators.*;
import org.uva.taxfree.ql.model.values.BooleanValue;
import org.uva.taxfree.ql.model.values.IntValue;
import org.uva.taxfree.ql.model.values.StringValue;
import org.uva.taxfree.ql.model.values.Value;
import test.org.uva.taxfree.ql.SemanticsTester;

public class BinaryExpressionTests extends SemanticsTester {

    @Test
    public void testGreaterEqualOperator() throws Exception {
        assertTrue(5, new GreaterEqualOperator(), 5);
        assertTrue(6, new GreaterEqualOperator(), 5);
        assertFalse(5, new GreaterEqualOperator(), 6);
    }

    @Test
    public void testGreaterThanOperator() throws Exception {
        assertFalse(5, new GreaterThanOperator(), 5);
        assertTrue(6, new GreaterThanOperator(), 5);
        assertFalse(5, new GreaterThanOperator(), 6);
    }

    @Test
    public void testLessEqualOperator() throws Exception {
        assertTrue(5, new LessEqualOperator(), 5);
        assertFalse(6, new LessEqualOperator(), 5);
        assertTrue(5, new LessEqualOperator(), 6);
    }

    @Test
    public void testLessThanOperator() throws Exception {
        assertFalse(5, new LessThanOperator(), 5);
        assertFalse(6, new LessThanOperator(), 5);
        assertTrue(5, new LessThanOperator(), 6);
    }

    @Test
    public void testEqualsOperator() throws Exception {
        assertTrue(5, new EqualsOperator(), 5);
        assertFalse(6, new EqualsOperator(), 5);
        assertFalse(5, new EqualsOperator(), 6);
        assertTrue(true, new EqualsOperator(), true);
        assertTrue(false, new EqualsOperator(), false);
        assertTrue(true, new EqualsOperator(), true);
        assertFalse(false, new EqualsOperator(), true);
        assertTrue("Hello", new EqualsOperator(), "Hello");
        assertFalse("Hello", new EqualsOperator(), "GoodBye");
        assertFalse("Goodbye", new EqualsOperator(), "Hello");
    }

    @Test
    public void testNotEqualsOperator() throws Exception {
        assertFalse(5, new NotEqualsOperator(), 5);
        assertTrue(6, new NotEqualsOperator(), 5);
        assertTrue(5, new NotEqualsOperator(), 6);
        assertFalse(true, new NotEqualsOperator(), true);
        assertFalse(false, new NotEqualsOperator(), false);
        assertFalse(true, new NotEqualsOperator(), true);
        assertTrue(false, new NotEqualsOperator(), true);
        assertFalse("Hello", new NotEqualsOperator(), "Hello");
        assertTrue("Hello", new NotEqualsOperator(), "GoodBye");
        assertTrue("Goodbye", new NotEqualsOperator(), "Hello");
    }

    @Test
    public void testAddOperator() throws Exception {
        assertThat(5, new AddOperator(), 6, 11);
        assertThat(-5, new AddOperator(), -10, -15);
    }

    @Test
    public void testSubtractOperator() throws Exception {
        assertThat(5, new SubtractOperator(), 6, -1);
        assertThat(-5, new SubtractOperator(), -10, 5);
    }

    @Test
    public void testMultiplyOperator() throws Exception {
        assertThat(10, new MultiplyOperator(), 5, 50);
        assertThat(-10, new MultiplyOperator(), 3, -30);
    }

    @Test
    public void testDivideOperator() throws Exception {
        assertThat(10, new DivideOperator(), 5, 2);
        assertThat(10, new DivideOperator(), 9, 1);
        assertThat(10, new DivideOperator(), 0, 0);
    }

    @Test
    public void testOrOperator() throws Exception {
        assertTrue(true, new OrOperator(), false);
        assertTrue(true, new OrOperator(), true);
        assertTrue(false, new OrOperator(), true);
        assertFalse(false, new OrOperator(), false);
    }

    @Test
    public void testAndOperator() throws Exception {
        assertFalse(true, new AndOperator(), false);
        assertTrue(true, new AndOperator(), true);
        assertFalse(false, new AndOperator(), true);
        assertFalse(false, new AndOperator(), false);
    }


    private void assertTrue(String leftHand, Operator operator, String rightHand) {
        assertThat(leftHand, operator, rightHand, true);
    }

    private void assertFalse(String leftHand, Operator operator, String rightHand) {
        assertThat(leftHand, operator, rightHand, false);
    }

    private void assertTrue(boolean leftHand, Operator operator, boolean rightHand) {
        assertThat(leftHand, operator, rightHand, true);
    }

    private void assertFalse(boolean leftHand, Operator operator, boolean rightHand) {
        assertThat(leftHand, operator, rightHand, false);
    }

    private void assertTrue(int leftHand, Operator operator, int rightHand) {
        assertThat(leftHand, operator, rightHand, new BooleanValue(true));
    }

    private void assertFalse(int leftHand, Operator operator, int rightHand) {
        assertThat(leftHand, operator, rightHand, new BooleanValue(false));
    }

    private void assertThat(String leftHand, Operator operator, String rightHand, boolean expected) {
        assertThat(new StringLiteralNode(leftHand, emptySource()), operator, new StringLiteralNode(rightHand, emptySource()), new BooleanValue(expected));
    }

    private void assertThat(boolean leftHand, Operator operator, boolean rightHand, boolean expected) {
        assertThat(new BooleanLiteralNode(leftHand, emptySource()), operator, new BooleanLiteralNode(rightHand, emptySource()), new BooleanValue(expected));
    }

    private void assertThat(int leftHand, Operator operator, int rightHand, Value expected) {
        assertThat(new IntegerLiteralNode(leftHand, emptySource()), operator, new IntegerLiteralNode(rightHand, emptySource()), expected);
    }

    private void assertThat(int leftHand, Operator operator, int rightHand, int expected) {
        assertThat(new IntegerLiteralNode(leftHand, emptySource()), operator, new IntegerLiteralNode(rightHand, emptySource()), new IntValue(expected));
    }

    private void assertThat(LiteralNode leftHand, Operator operator, LiteralNode rightHand, Value expectedValue) {
        BinaryExpressionNode expression = new BinaryExpressionNode(leftHand, operator, rightHand, emptySource());
        System.out.println(expression.evaluate());
        Assert.assertTrue(expression.evaluate().equalsToValue(expectedValue));
    }

    private SourceInfo emptySource() {
        return new SourceInfo(0, 0, 0, 0);
    }

    @Override
    protected String fileDirectory() {
        return "sampleCalculations";
    }
}
