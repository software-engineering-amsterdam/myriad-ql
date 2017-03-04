package org.ql.evaluator;

import org.junit.Test;
import org.ql.ast.expression.arithmetic.Product;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.DecimalLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.ast.expression.relational.Equals;
import org.ql.ast.expression.relational.LogicalOr;
import org.ql.ast.expression.relational.LowerThanOrEqual;
import org.ql.ast.expression.relational.NotEqual;
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

        System.out.println(actualBooleanValue.getPlainValue());

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
    public void shouldReturnIntegerOnProductUsingIntegers() {
        Evaluator evaluator = new Evaluator();

        Product actualProduct = new Product(new IntegerLiteral(2), new IntegerLiteral(3));
        IntegerValue actualProductValue = (IntegerValue) evaluator.visit(actualProduct, null);

        assertSame(6, actualProductValue.getPlainValue());
    }


}