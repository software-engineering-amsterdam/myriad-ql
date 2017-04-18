package org.uva.hatt.taxform.evaluation;

import org.junit.Before;
import org.junit.Test;
import org.uva.hatt.taxform.ast.nodes.expressions.binary.*;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.BooleanLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.IntegerLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.StringerLiteral;
import org.uva.hatt.taxform.values.BooleanValue;
import org.uva.hatt.taxform.values.IntegerValue;
import org.uva.hatt.taxform.values.StringValue;

import static org.junit.Assert.*;

public class EvaluatorTest {

    private Evaluator evaluator;

    @Before
    public void setUp() throws Exception {
        evaluator = new Evaluator(new Environment());
    }

    @Test
    public void testStringLiteral() throws Exception {
        StringerLiteral stringerLiteral = new StringerLiteral(1, "ql");
        StringValue stringValue = (StringValue) evaluator.visit(stringerLiteral);

        assertEquals(stringerLiteral.getValue(), stringValue.getValue());
    }

    @Test
    public void testIntegerLiteral() throws Exception {
        IntegerLiteral integerLiteral = new IntegerLiteral(1, 1);
        IntegerValue integerValue = (IntegerValue) evaluator.visit(integerLiteral);

        assertEquals(integerLiteral.getValue(), (int) integerValue.getValue());
    }

    @Test
    public void testBooleanLiteral() throws Exception {
        BooleanLiteral booleanLiteral = new BooleanLiteral(1, true);
        BooleanValue booleanValue = (BooleanValue) evaluator.visit(booleanLiteral);

        assertEquals(booleanLiteral.isValue(), booleanValue.getValue());
    }

    @Test
    public void testAdditionIntegerLiteral() throws Exception {
        Addition addition = new Addition(1, new IntegerLiteral(1, 6), new IntegerLiteral(1, 2));
        IntegerValue value = (IntegerValue) evaluator.visit(addition);

        assertEquals(new Integer(8), value.getValue());
    }

    @Test
    public void testAdditionStringLiteral() throws Exception {
        Addition addition = new Addition(1, new StringerLiteral(1, "q"), new StringerLiteral(1, "l"));
        StringValue value = (StringValue) evaluator.visit(addition);

        assertEquals("ql", value.getValue());
    }

    @Test
    public void testAdditionStringLiteralEmptyString() throws Exception {
        Addition addition = new Addition(1, new StringerLiteral(1, ""), new StringerLiteral(1, ""));
        StringValue value = (StringValue) evaluator.visit(addition);

        assertEquals("", value.getValue());
    }

    @Test
    public void testDivision() throws Exception {
        Division division = new Division(1, new IntegerLiteral(1, 6), new IntegerLiteral(1, 2));
        IntegerValue value = (IntegerValue) evaluator.visit(division);

        assertEquals(new Integer(3), value.getValue());
    }

    @Test
    public void testEqualBooleanLiteralTrue() throws Exception {
        Equal equal = new Equal(1, new BooleanLiteral(1, true), new BooleanLiteral(1, true));
        BooleanValue value = (BooleanValue) evaluator.visit(equal);

        assertEquals(true, value.getValue());
    }

    @Test
    public void testEqualBooleanLiteralFalse() throws Exception {
        Equal equal = new Equal(1, new BooleanLiteral(1, true), new BooleanLiteral(1, false));
        BooleanValue value = (BooleanValue) evaluator.visit(equal);

        assertEquals(false, value.getValue());
    }

    @Test
    public void testEqualIntegerLiteralTrue() throws Exception {
        Equal equal = new Equal(1, new IntegerLiteral(1, 1), new IntegerLiteral(1, 1));
        BooleanValue value = (BooleanValue) evaluator.visit(equal);

        assertEquals(true, value.getValue());
    }

    @Test
    public void testEqualIntegerLiteralFalse() throws Exception {
        Equal equal = new Equal(1, new IntegerLiteral(1, 1), new IntegerLiteral(1, 2));
        BooleanValue value = (BooleanValue) evaluator.visit(equal);

        assertEquals(false, value.getValue());
    }

    @Test
    public void testEqualStringLiteralTrue() throws Exception {
        Equal equal = new Equal(1, new StringerLiteral(1, "a"), new StringerLiteral(1, "a"));
        BooleanValue value = (BooleanValue) evaluator.visit(equal);

        assertEquals(true, value.getValue());
    }

    @Test
    public void testEqualStringLiteralFalse() throws Exception {
        Equal equal = new Equal(1, new StringerLiteral(1, "a"), new StringerLiteral(1, "b"));
        BooleanValue value = (BooleanValue) evaluator.visit(equal);

        assertEquals(false, value.getValue());
    }

    @Test
    public void testGreaterThanTrue() throws Exception {
        GreaterThan lessThan = new GreaterThan(1, new IntegerLiteral(1, 6), new IntegerLiteral(1, 2));
        BooleanValue value = (BooleanValue) evaluator.visit(lessThan);

        assertEquals(true, value.getValue());
    }

    @Test
    public void testGreaterThanFalse() throws Exception {
        GreaterThan lessThan = new GreaterThan(1, new IntegerLiteral(1, 2), new IntegerLiteral(1, 6));
        BooleanValue value = (BooleanValue) evaluator.visit(lessThan);

        assertEquals(false, value.getValue());
    }

    @Test
    public void testGreaterThanOrEqualTrue() throws Exception {
        GreaterThanOrEqual lessThan = new GreaterThanOrEqual(1, new IntegerLiteral(1, 6), new IntegerLiteral(1, 2));
        BooleanValue value = (BooleanValue) evaluator.visit(lessThan);

        assertEquals(true, value.getValue());
    }

    @Test
    public void testGreaterThanOrEqualTrueBothEqual() throws Exception {
        GreaterThanOrEqual lessThan = new GreaterThanOrEqual(1, new IntegerLiteral(1, 6), new IntegerLiteral(1, 6));
        BooleanValue value = (BooleanValue) evaluator.visit(lessThan);

        assertEquals(true, value.getValue());
    }

    @Test
    public void testGreaterThanOrEqualFalse() throws Exception {
        GreaterThanOrEqual lessThan = new GreaterThanOrEqual(1, new IntegerLiteral(1, 2), new IntegerLiteral(1, 6));
        BooleanValue value = (BooleanValue) evaluator.visit(lessThan);

        assertEquals(false, value.getValue());
    }

    @Test
    public void testLessThanTrue() throws Exception {
        LessThan lessThan = new LessThan(1, new IntegerLiteral(1, 2), new IntegerLiteral(1, 6));
        BooleanValue value = (BooleanValue) evaluator.visit(lessThan);

        assertEquals(true, value.getValue());
    }

    @Test
    public void testLessThanFalse() throws Exception {
        LessThan lessThan = new LessThan(1, new IntegerLiteral(1, 6), new IntegerLiteral(1, 2));
        BooleanValue value = (BooleanValue) evaluator.visit(lessThan);

        assertEquals(false, value.getValue());
    }

    @Test
    public void testLessThanOrEqualTrue() throws Exception {
        LessThanOrEqual lessThan = new LessThanOrEqual(1, new IntegerLiteral(1, 2), new IntegerLiteral(1, 6));
        BooleanValue value = (BooleanValue) evaluator.visit(lessThan);

        assertEquals(true, value.getValue());
    }

    @Test
    public void testLessThanOrEqualTrueBothEqual() throws Exception {
        LessThanOrEqual lessThan = new LessThanOrEqual(1, new IntegerLiteral(1, 6), new IntegerLiteral(1, 6));
        BooleanValue value = (BooleanValue) evaluator.visit(lessThan);

        assertEquals(true, value.getValue());
    }

    @Test
    public void testLessThanOrEqualFalse() throws Exception {
        LessThanOrEqual lessThan = new LessThanOrEqual(1, new IntegerLiteral(1, 6), new IntegerLiteral(1, 2));
        BooleanValue value = (BooleanValue) evaluator.visit(lessThan);

        assertEquals(false, value.getValue());
    }

    @Test
    public void testLogicalAndBooleanLiteralTrue() throws Exception {
        LogicalAnd logicalAnd = new LogicalAnd(1, new BooleanLiteral(1, true), new BooleanLiteral(1, true));
        BooleanValue value = (BooleanValue) evaluator.visit(logicalAnd);

        assertEquals(true, value.getValue());
    }

    @Test
    public void testLogicalAndBooleanLiteralFalse() throws Exception {
        LogicalAnd logicalAnd = new LogicalAnd(1, new BooleanLiteral(1, true), new BooleanLiteral(1, false));
        BooleanValue value = (BooleanValue) evaluator.visit(logicalAnd);

        assertEquals(false, value.getValue());
    }

    @Test
    public void testLogicalOrBooleanLiteralTrue() throws Exception {
        LogicalOr logicalOr = new LogicalOr(1, new BooleanLiteral(1, true), new BooleanLiteral(1, true));
        BooleanValue value = (BooleanValue) evaluator.visit(logicalOr);

        assertEquals(true, value.getValue());
    }

    @Test
    public void testLogicalOrBooleanLiteralFalse() throws Exception {
        LogicalOr logicalOr = new LogicalOr(1, new BooleanLiteral(1, false), new BooleanLiteral(1, false));
        BooleanValue value = (BooleanValue) evaluator.visit(logicalOr);

        assertEquals(false, value.getValue());
    }

    @Test
    public void testLogicalOrBooleanLiteralLeftTrue() throws Exception {
        LogicalOr logicalOr = new LogicalOr(1, new BooleanLiteral(1, true), new BooleanLiteral(1, false));
        BooleanValue value = (BooleanValue) evaluator.visit(logicalOr);

        assertEquals(true, value.getValue());
    }

    @Test
    public void testLogicalOrBooleanLiteralRightTrue() throws Exception {
        LogicalOr logicalOr = new LogicalOr(1, new BooleanLiteral(1, false), new BooleanLiteral(1, true));
        BooleanValue value = (BooleanValue) evaluator.visit(logicalOr);

        assertEquals(true, value.getValue());
    }

    @Test
    public void testMultiplication() throws Exception {
        Multiplication multiplication = new Multiplication(1, new IntegerLiteral(1, 6), new IntegerLiteral(1, 2));
        IntegerValue value = (IntegerValue) evaluator.visit(multiplication);

        assertEquals(new Integer(12), value.getValue());
    }

    @Test
    public void testNotEqualBooleanLiteralTrue() throws Exception {
        NotEqual notEqual = new NotEqual(1, new BooleanLiteral(1, true), new BooleanLiteral(1, false));
        BooleanValue value = (BooleanValue) evaluator.visit(notEqual);

        assertEquals(true, value.getValue());
    }

    @Test
    public void testNotEqualBooleanLiteralFalse() throws Exception {
        NotEqual notEqual = new NotEqual(1, new BooleanLiteral(1, true), new BooleanLiteral(1, true));
        BooleanValue value = (BooleanValue) evaluator.visit(notEqual);

        assertEquals(false, value.getValue());
    }

    @Test
    public void testNotEqualIntegerLiteralTrue() throws Exception {
        NotEqual notEqual = new NotEqual(1, new IntegerLiteral(1, 1), new IntegerLiteral(1, 2));
        BooleanValue value = (BooleanValue) evaluator.visit(notEqual);

        assertEquals(true, value.getValue());
    }

    @Test
    public void testNotEqualIntegerLiteralFalse() throws Exception {
        NotEqual notEqual = new NotEqual(1, new IntegerLiteral(1, 1), new IntegerLiteral(1, 1));
        BooleanValue value = (BooleanValue) evaluator.visit(notEqual);

        assertEquals(false, value.getValue());
    }

    @Test
    public void testNotEqualStringLiteralTrue() throws Exception {
        NotEqual notEqual = new NotEqual(1, new StringerLiteral(1, "a"), new StringerLiteral(1, "b"));
        BooleanValue value = (BooleanValue) evaluator.visit(notEqual);

        assertEquals(true, value.getValue());
    }

    @Test
    public void testNotEqualStringLiteralFalse() throws Exception {
        NotEqual notEqual = new NotEqual(1, new StringerLiteral(1, "a"), new StringerLiteral(1, "a"));
        BooleanValue value = (BooleanValue) evaluator.visit(notEqual);

        assertEquals(false, value.getValue());
    }

    @Test
    public void testSubtraction() throws Exception {
        Subtraction subtraction = new Subtraction(1, new IntegerLiteral(1, 6), new IntegerLiteral(1, 2));
        IntegerValue value = (IntegerValue) evaluator.visit(subtraction);

        assertEquals(new Integer(4), value.getValue());
    }

}