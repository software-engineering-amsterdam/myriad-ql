package org.ql.evaluator;

import org.junit.Test;
import org.ql.ast.expression.arithmetic.*;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.DecimalLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.ast.expression.relational.*;
import org.ql.evaluator.value.BooleanValue;
import org.ql.evaluator.value.DecimalValue;
import org.ql.evaluator.value.IntegerValue;
import org.ql.evaluator.value.StringValue;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class EvaluatorTest {

    @Test
    public void shouldReturnStringValueFromStringLiteral() {
        Evaluator evaluator = new Evaluator();

        String exampleString = "example";
        StringValue actualStringValue = evaluator.visit(new StringLiteral(exampleString), null);

        assertEquals(actualStringValue.getPlainValue(), exampleString);
    }

    @Test
    public void shouldReturnBooleanValueFromBooleanLiteral() {
        Evaluator evaluator = new Evaluator();

        BooleanValue actualBooleanValue = evaluator.visit(new BooleanLiteral(true), null);

        assertEquals(actualBooleanValue.getPlainValue(), true);
    }

    @Test
    public void shouldReturnIntegerValueFromIntegerLiteral() {
        Evaluator evaluator = new Evaluator();

        IntegerValue actualIntegerValue = evaluator.visit(new IntegerLiteral(1234), null);

        assertEquals(actualIntegerValue.getPlainValue().intValue(), 1234);
    }

    @Test
    public void shouldReturnDecimalValueFromDecimalLiteral() {
        Evaluator evaluator = new Evaluator();

        DecimalValue actualDecimalValue = evaluator.visit(new DecimalLiteral(new BigDecimal(12.33)), null);

        assertEquals(actualDecimalValue.getPlainValue(), new BigDecimal(12.33));
    }

    @Test
    public void shouldReturnBooleanTrueValueFromLogicalOr() {
        Evaluator evaluator = new Evaluator();

        LogicalOr logicalOr = new LogicalOr(new BooleanLiteral(true), new BooleanLiteral(false));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(logicalOr, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueFromLogicalOr() {
        Evaluator evaluator = new Evaluator();

        LogicalOr logicalOr = new LogicalOr(new BooleanLiteral(false), new BooleanLiteral(false));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(logicalOr, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueFromLowerThanOrEqualOnLowerUsingIntegers() {
        Evaluator evaluator = new Evaluator();

        LowerThanOrEqual lowerThanOrEqual = new LowerThanOrEqual(new IntegerLiteral(1), new IntegerLiteral(2));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(lowerThanOrEqual, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueFromLowerThanOrEqualOnEqualUsingIntegers() {
        Evaluator evaluator = new Evaluator();

        LowerThanOrEqual lowerThanOrEqual = new LowerThanOrEqual(new IntegerLiteral(1), new IntegerLiteral(1));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(lowerThanOrEqual, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueFromLowerThanOrEqualOnGreaterUsingIntegers() {
        Evaluator evaluator = new Evaluator();

        LowerThanOrEqual lowerThanOrEqual = new LowerThanOrEqual(new IntegerLiteral(2), new IntegerLiteral(1));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(lowerThanOrEqual, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueFromLowerThanOrEqualOnLowerUsingDecimals() {
        Evaluator evaluator = new Evaluator();

        LowerThanOrEqual lowerThanOrEqual = new LowerThanOrEqual(
                new DecimalLiteral(new BigDecimal(1.1)), new DecimalLiteral(new BigDecimal(2.0)));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(lowerThanOrEqual, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueFromLowerThanOrEqualOnEqualUsingDecimals() {
        Evaluator evaluator = new Evaluator();

        LowerThanOrEqual lowerThanOrEqual = new LowerThanOrEqual(
                new DecimalLiteral(new BigDecimal(1.1)), new DecimalLiteral(new BigDecimal(1.1)));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(lowerThanOrEqual, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueFromLowerThanOrEqualOnGreaterUsingDecimals() {
        Evaluator evaluator = new Evaluator();

        LowerThanOrEqual lowerThanOrEqual = new LowerThanOrEqual(
                new DecimalLiteral(new BigDecimal(3.4)), new DecimalLiteral(new BigDecimal(1.1)));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(lowerThanOrEqual, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueOnEqualsUsingDecimals() {
        Evaluator evaluator = new Evaluator();

        Equals equals = new Equals(new DecimalLiteral(new BigDecimal(3.4)), new DecimalLiteral(new BigDecimal(3.4)));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(equals, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueOnEqualsUsingDecimals() {
        Evaluator evaluator = new Evaluator();

        Equals equals = new Equals(
                new DecimalLiteral(new BigDecimal(3.4)), new DecimalLiteral(new BigDecimal(53.24))
        );
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(equals, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueOnEqualsUsingIntegers() {
        Evaluator evaluator = new Evaluator();

        Equals actualEquals = new Equals(new IntegerLiteral(3), new IntegerLiteral(3));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(actualEquals, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueOnEqualsUsingIntegers() {
        Evaluator evaluator = new Evaluator();

        Equals actualEquals = new Equals(new IntegerLiteral(3), new IntegerLiteral(23));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(actualEquals, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueOnEqualsUsingStrings() {
        Evaluator evaluator = new Evaluator();

        Equals actualEquals = new Equals(new StringLiteral("example"), new StringLiteral("example"));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(actualEquals, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueOnEqualsUsingStrings() {
        Evaluator evaluator = new Evaluator();

        Equals actualEquals = new Equals(new StringLiteral("example"), new StringLiteral("exampl232e"));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(actualEquals, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueOnNotEqualsUsingStrings() {
        Evaluator evaluator = new Evaluator();

        NotEqual actualNotEquals = new NotEqual(new StringLiteral("example"), new StringLiteral("exampl232e"));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(actualNotEquals, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueOnNotEqualsUsingStrings() {
        Evaluator evaluator = new Evaluator();

        NotEqual actualNotEquals = new NotEqual(new StringLiteral("example"), new StringLiteral("example"));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(actualNotEquals, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueOnNotEqualsUsingDecimals() {
        Evaluator evaluator = new Evaluator();

        NotEqual actualNotEquals = new NotEqual(new DecimalLiteral(new BigDecimal(5)), new DecimalLiteral(new BigDecimal(4)));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(actualNotEquals, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueOnNotEqualsUsingDecimals() {
        Evaluator evaluator = new Evaluator();

        NotEqual actualNotEquals = new NotEqual(new DecimalLiteral(new BigDecimal(4)), new DecimalLiteral(new BigDecimal(4)));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(actualNotEquals, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueOnNotEqualsUsingIntegers() {
        Evaluator evaluator = new Evaluator();

        NotEqual actualNotEquals = new NotEqual(new IntegerLiteral(4), new IntegerLiteral(3));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(actualNotEquals, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueOnNotEqualsUsingIntegers() {
        Evaluator evaluator = new Evaluator();

        NotEqual actualNotEquals = new NotEqual(new IntegerLiteral(3), new IntegerLiteral(3));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(actualNotEquals, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnIntegerOnProductUsingIntegers() {
        Evaluator evaluator = new Evaluator();

        Product actualProduct = new Product(new IntegerLiteral(2), new IntegerLiteral(3));
        IntegerValue actualProductValue = (IntegerValue) evaluator.visit(actualProduct, null);

        assertSame(6, actualProductValue.getPlainValue());
    }

    @Test
    public void shouldReturnIntegerOnProductUsingDecimals() {
        Evaluator evaluator = new Evaluator();

        Product actualProduct = new Product(new DecimalLiteral(new BigDecimal(5)), new DecimalLiteral(new BigDecimal(3)));
        DecimalValue actualProductValue = (DecimalValue) evaluator.visit(actualProduct, null);

        assertEquals(new BigDecimal(15), actualProductValue.getPlainValue());
    }

    @Test
    public void shouldReturnIntegerOnIncrementUsingIntegers() {
        Evaluator evaluator = new Evaluator();

        Increment actualIncrement = new Increment(new IntegerLiteral(5));
        IntegerValue actualIncrementValue = (IntegerValue) evaluator.visit(actualIncrement, null);

        assertSame(6, actualIncrementValue.getPlainValue());
    }

    @Test
    public void shouldReturnDecimalOnIncrementUsingDecimals() {
        Evaluator evaluator = new Evaluator();

        Increment actualIncrement = new Increment(new DecimalLiteral(new BigDecimal(5)));
        DecimalValue actualIncrementValue = (DecimalValue) evaluator.visit(actualIncrement, null);

        assertEquals(new BigDecimal(6), actualIncrementValue.getPlainValue());
    }

    @Test
    public void shouldReturnIntegerOnDecrementUsingIntegers() {
        Evaluator evaluator = new Evaluator();

        Decrement actualDecrement = new Decrement(new IntegerLiteral(5));
        IntegerValue actualDecrementValue = (IntegerValue) evaluator.visit(actualDecrement, null);

        assertSame(4, actualDecrementValue.getPlainValue());
    }

    @Test
    public void shouldReturnDecimalOnDecrementUsingDecimals() {
        Evaluator evaluator = new Evaluator();

        Decrement actualDecrement = new Decrement(new DecimalLiteral(new BigDecimal(5)));
        DecimalValue actualDecrementValue = (DecimalValue) evaluator.visit(actualDecrement, null);

        assertEquals(new BigDecimal(4), actualDecrementValue.getPlainValue());
    }

    @Test
    public void shouldReturnIntegerOnSubtractionUsingIntegers() {
        Evaluator evaluator = new Evaluator();

        Subtraction actualSubtraction = new Subtraction(new IntegerLiteral(5), new IntegerLiteral(3));
        IntegerValue actualSubtractionValue = (IntegerValue) evaluator.visit(actualSubtraction, null);

        assertSame(2, actualSubtractionValue.getPlainValue());
    }

    @Test
    public void shouldReturnDecimalOnSubtractionUsingDecimals() {
        Evaluator evaluator = new Evaluator();

        Subtraction actualSubtraction = new Subtraction(new DecimalLiteral(new BigDecimal(5)), new DecimalLiteral(new BigDecimal(3)));
        DecimalValue actualSubtractionValue = (DecimalValue) evaluator.visit(actualSubtraction, null);

        assertEquals(new BigDecimal(2), actualSubtractionValue.getPlainValue());
    }

    @Test
    public void shouldReturnIntegerOnAdditionUsingIntegers() {
        Evaluator evaluator = new Evaluator();

        Addition actualAddition = new Addition(new IntegerLiteral(5), new IntegerLiteral(3));
        IntegerValue actualAdditionValue = (IntegerValue) evaluator.visit(actualAddition, null);

        assertSame(8, actualAdditionValue.getPlainValue());
    }

    @Test
    public void shouldReturnDecimalOnAdditionUsingDecimals() {
        Evaluator evaluator = new Evaluator();

        Addition actualAddition = new Addition(new DecimalLiteral(new BigDecimal(5)), new DecimalLiteral(new BigDecimal(3)));
        DecimalValue actualAdditionValue = (DecimalValue) evaluator.visit(actualAddition, null);

        assertEquals(new BigDecimal(8), actualAdditionValue.getPlainValue());
    }

    @Test
    public void shouldReturnIntegerOnDivisionUsingIntegers() {
        Evaluator evaluator = new Evaluator();

        Division actualDivision = new Division(new IntegerLiteral(11), new IntegerLiteral(2));
        IntegerValue actualDivisionValue = (IntegerValue) evaluator.visit(actualDivision, null);

        assertSame(5, actualDivisionValue.getPlainValue());
    }

    @Test
    public void shouldReturnIntegerWhenEncapsulatedAsGroup() {
        Evaluator evaluator = new Evaluator();

        Group actualGroup = new Group(new IntegerLiteral(3));
        IntegerValue actualIntegerValue = (IntegerValue) evaluator.visit(actualGroup, null);

        assertSame(3, actualIntegerValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueFromGreaterThanUsingIntegers() {
        Evaluator evaluator = new Evaluator();

        GreaterThan greaterThan = new GreaterThan(new IntegerLiteral(5), new IntegerLiteral(8));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(greaterThan, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueFromGreaterThanUsingIntegers() {
        Evaluator evaluator = new Evaluator();

        GreaterThan greaterThan = new GreaterThan(new IntegerLiteral(5), new IntegerLiteral(2));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(greaterThan, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueFromGreaterThanUsingDecimals() {
        Evaluator evaluator = new Evaluator();

        GreaterThan greaterThan = new GreaterThan(new DecimalLiteral(new BigDecimal(3)), new DecimalLiteral(new BigDecimal(8)));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(greaterThan, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueFromGreaterThanUsingDecimals() {
        Evaluator evaluator = new Evaluator();

        GreaterThan greaterThan = new GreaterThan(new DecimalLiteral(new BigDecimal(10)), new DecimalLiteral(new BigDecimal(8)));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(greaterThan, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueFromNegation() {
        Evaluator evaluator = new Evaluator();

        Negation greaterThan = new Negation(new BooleanLiteral(false));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(greaterThan, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueFromNegation() {
        Evaluator evaluator = new Evaluator();

        Negation greaterThan = new Negation(new BooleanLiteral(true));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(greaterThan, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueFromLogicalAnd() {
        Evaluator evaluator = new Evaluator();

        LogicalAnd logicalAnd = new LogicalAnd(new BooleanLiteral(true), new BooleanLiteral(false));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(logicalAnd, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueFromLogicalAnd() {
        Evaluator evaluator = new Evaluator();

        LogicalAnd logicalAnd = new LogicalAnd(new BooleanLiteral(true), new BooleanLiteral(true));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(logicalAnd, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueFromGreaterOrEqualThanUsingIntegers() {
        Evaluator evaluator = new Evaluator();

        GreaterThanOrEqual greaterThanOrEqual = new GreaterThanOrEqual(new IntegerLiteral(5), new IntegerLiteral(8));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(greaterThanOrEqual, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueFromGreaterOrEqualThanUsingIntegers() {
        Evaluator evaluator = new Evaluator();

        GreaterThanOrEqual greaterThanOrEqual = new GreaterThanOrEqual(new IntegerLiteral(5), new IntegerLiteral(2));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(greaterThanOrEqual, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueFromGreaterThanOrEqualUsingDecimals() {
        Evaluator evaluator = new Evaluator();

        GreaterThanOrEqual greaterThanOrEqual = new GreaterThanOrEqual(new DecimalLiteral(new BigDecimal(3)), new DecimalLiteral(new BigDecimal(8)));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(greaterThanOrEqual, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueFromGreaterThanOrEqualUsingDecimals() {
        Evaluator evaluator = new Evaluator();

        GreaterThanOrEqual greaterThanOrEqual = new GreaterThanOrEqual(new DecimalLiteral(new BigDecimal(10)), new DecimalLiteral(new BigDecimal(8)));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(greaterThanOrEqual, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueFromGreaterOrEqualOnEqualThanUsingIntegers() {
        Evaluator evaluator = new Evaluator();

        GreaterThanOrEqual greaterThanOrEqual = new GreaterThanOrEqual(new IntegerLiteral(5), new IntegerLiteral(5));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(greaterThanOrEqual, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueFromGreaterThanOrEqualOnEqualUsingDecimals() {
        Evaluator evaluator = new Evaluator();

        GreaterThanOrEqual greaterThanOrEqual = new GreaterThanOrEqual(new DecimalLiteral(new BigDecimal(3)), new DecimalLiteral(new BigDecimal(3)));
        BooleanValue actualBooleanValue = (BooleanValue) evaluator.visit(greaterThanOrEqual, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }
}