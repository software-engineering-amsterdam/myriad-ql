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

public class ExpressionEvaluatorTest {

    @Test
    public void shouldReturnStringValueFromStringLiteral() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        String exampleString = "example";
        StringValue actualStringValue = expressionEvaluator.visitString(new StringLiteral(exampleString), null);

        assertEquals(actualStringValue.getPlainValue(), exampleString);
    }

    @Test
    public void shouldReturnBooleanValueFromBooleanLiteral() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        BooleanValue actualBooleanValue = expressionEvaluator.visitBoolean(new BooleanLiteral(true), null);

        assertEquals(actualBooleanValue.getPlainValue(), true);
    }

    @Test
    public void shouldReturnIntegerValueFromIntegerLiteral() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        IntegerValue actualIntegerValue = expressionEvaluator.visitInteger(new IntegerLiteral(1234), null);

        assertEquals(actualIntegerValue.getPlainValue().intValue(), 1234);
    }

    @Test
    public void shouldReturnDecimalValueFromDecimalLiteral() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        DecimalValue actualDecimalValue = expressionEvaluator.visitDecimal(new DecimalLiteral(new BigDecimal(12.33)), null);

        assertEquals(actualDecimalValue.getPlainValue(), new BigDecimal(12.33));
    }

    @Test
    public void shouldReturnBooleanTrueValueFromLogicalOr() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        LogicalOr logicalOr = new LogicalOr(new BooleanLiteral(true), new BooleanLiteral(false));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitOr(logicalOr, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueFromLogicalOr() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        LogicalOr logicalOr = new LogicalOr(new BooleanLiteral(false), new BooleanLiteral(false));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitOr(logicalOr, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueFromLowerThanOrEqualOnLowerUsingIntegers() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        LowerThanOrEqual lowerThanOrEqual = new LowerThanOrEqual(new IntegerLiteral(1), new IntegerLiteral(2));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitLowerThanOrEqual(lowerThanOrEqual, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueFromLowerThanOrEqualOnEqualUsingIntegers() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        LowerThanOrEqual lowerThanOrEqual = new LowerThanOrEqual(new IntegerLiteral(1), new IntegerLiteral(1));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitLowerThanOrEqual(lowerThanOrEqual, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueFromLowerThanOrEqualOnGreaterUsingIntegers() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        LowerThanOrEqual lowerThanOrEqual = new LowerThanOrEqual(new IntegerLiteral(2), new IntegerLiteral(1));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitLowerThanOrEqual(lowerThanOrEqual, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueFromLowerThanOrEqualOnLowerUsingDecimals() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        LowerThanOrEqual lowerThanOrEqual = new LowerThanOrEqual(
                new DecimalLiteral(new BigDecimal(1.1)), new DecimalLiteral(new BigDecimal(2.0)));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitLowerThanOrEqual(lowerThanOrEqual, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueFromLowerThanOrEqualOnEqualUsingDecimals() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        LowerThanOrEqual lowerThanOrEqual = new LowerThanOrEqual(
                new DecimalLiteral(new BigDecimal(1.1)), new DecimalLiteral(new BigDecimal(1.1)));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitLowerThanOrEqual(lowerThanOrEqual, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueFromLowerThanOrEqualOnGreaterUsingDecimals() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        LowerThanOrEqual lowerThanOrEqual = new LowerThanOrEqual(
                new DecimalLiteral(new BigDecimal(3.4)), new DecimalLiteral(new BigDecimal(1.1)));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitLowerThanOrEqual(lowerThanOrEqual, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueOnEqualsUsingDecimals() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        Equals equals = new Equals(new DecimalLiteral(new BigDecimal(3.4)), new DecimalLiteral(new BigDecimal(3.4)));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitEquals(equals, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueOnEqualsUsingDecimals() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        Equals equals = new Equals(
                new DecimalLiteral(new BigDecimal(3.4)), new DecimalLiteral(new BigDecimal(53.24))
        );
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitEquals(equals, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueOnEqualsUsingIntegers() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        Equals actualEquals = new Equals(new IntegerLiteral(3), new IntegerLiteral(3));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitEquals(actualEquals, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueOnEqualsUsingIntegers() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        Equals actualEquals = new Equals(new IntegerLiteral(3), new IntegerLiteral(23));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitEquals(actualEquals, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueOnEqualsUsingStrings() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        Equals actualEquals = new Equals(new StringLiteral("example"), new StringLiteral("example"));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitEquals(actualEquals, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueOnEqualsUsingStrings() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        Equals actualEquals = new Equals(new StringLiteral("example"), new StringLiteral("exampl232e"));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitEquals(actualEquals, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueOnNotEqualsUsingStrings() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        NotEqual actualNotEquals = new NotEqual(new StringLiteral("example"), new StringLiteral("exampl232e"));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitNotEqual(actualNotEquals, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueOnNotEqualsUsingStrings() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        NotEqual actualNotEquals = new NotEqual(new StringLiteral("example"), new StringLiteral("example"));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitNotEqual(actualNotEquals, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueOnNotEqualsUsingDecimals() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        NotEqual actualNotEquals = new NotEqual(new DecimalLiteral(new BigDecimal(5)), new DecimalLiteral(new BigDecimal(4)));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitNotEqual(actualNotEquals, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueOnNotEqualsUsingDecimals() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        NotEqual actualNotEquals = new NotEqual(new DecimalLiteral(new BigDecimal(4)), new DecimalLiteral(new BigDecimal(4)));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitNotEqual(actualNotEquals, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueOnNotEqualsUsingIntegers() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        NotEqual actualNotEquals = new NotEqual(new IntegerLiteral(4), new IntegerLiteral(3));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitNotEqual(actualNotEquals, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueOnNotEqualsUsingIntegers() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        NotEqual actualNotEquals = new NotEqual(new IntegerLiteral(3), new IntegerLiteral(3));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitNotEqual(actualNotEquals, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnIntegerOnProductUsingIntegers() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        Product actualProduct = new Product(new IntegerLiteral(2), new IntegerLiteral(3));
        IntegerValue actualProductValue = (IntegerValue) expressionEvaluator.visitProduct(actualProduct, null);

        assertSame(6, actualProductValue.getPlainValue());
    }

    @Test
    public void shouldReturnIntegerOnProductUsingDecimals() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        Product actualProduct = new Product(new DecimalLiteral(new BigDecimal(5)), new DecimalLiteral(new BigDecimal(3)));
        DecimalValue actualProductValue = (DecimalValue) expressionEvaluator.visitProduct(actualProduct, null);

        assertEquals(new BigDecimal(15), actualProductValue.getPlainValue());
    }

    @Test
    public void shouldReturnIntegerOnIncrementUsingIntegers() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        Increment actualIncrement = new Increment(new IntegerLiteral(5));
        IntegerValue actualIncrementValue = (IntegerValue) expressionEvaluator.visitIncrement(actualIncrement, null);

        assertSame(6, actualIncrementValue.getPlainValue());
    }

    @Test
    public void shouldReturnDecimalOnIncrementUsingDecimals() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        Increment actualIncrement = new Increment(new DecimalLiteral(new BigDecimal(5)));
        DecimalValue actualIncrementValue = (DecimalValue) expressionEvaluator.visitIncrement(actualIncrement, null);

        assertEquals(new BigDecimal(6), actualIncrementValue.getPlainValue());
    }

    @Test
    public void shouldReturnIntegerOnDecrementUsingIntegers() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        Decrement actualDecrement = new Decrement(new IntegerLiteral(5));
        IntegerValue actualDecrementValue = (IntegerValue) expressionEvaluator.visitDecrement(actualDecrement, null);

        assertSame(4, actualDecrementValue.getPlainValue());
    }

    @Test
    public void shouldReturnDecimalOnDecrementUsingDecimals() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        Decrement actualDecrement = new Decrement(new DecimalLiteral(new BigDecimal(5)));
        DecimalValue actualDecrementValue = (DecimalValue) expressionEvaluator.visitDecrement(actualDecrement, null);

        assertEquals(new BigDecimal(4), actualDecrementValue.getPlainValue());
    }

    @Test
    public void shouldReturnIntegerOnSubtractionUsingIntegers() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        Subtraction actualSubtraction = new Subtraction(new IntegerLiteral(5), new IntegerLiteral(3));
        IntegerValue actualSubtractionValue = (IntegerValue) expressionEvaluator.visitSubtraction(actualSubtraction, null);

        assertSame(2, actualSubtractionValue.getPlainValue());
    }

    @Test
    public void shouldReturnDecimalOnSubtractionUsingDecimals() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        Subtraction actualSubtraction = new Subtraction(new DecimalLiteral(new BigDecimal(5)), new DecimalLiteral(new BigDecimal(3)));
        DecimalValue actualSubtractionValue = (DecimalValue) expressionEvaluator.visitSubtraction(actualSubtraction, null);

        assertEquals(new BigDecimal(2), actualSubtractionValue.getPlainValue());
    }

    @Test
    public void shouldReturnIntegerOnAdditionUsingIntegers() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        Addition actualAddition = new Addition(new IntegerLiteral(5), new IntegerLiteral(3));
        IntegerValue actualAdditionValue = (IntegerValue) expressionEvaluator.visitAddition(actualAddition, null);

        assertSame(8, actualAdditionValue.getPlainValue());
    }

    @Test
    public void shouldReturnDecimalOnAdditionUsingDecimals() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        Addition actualAddition = new Addition(new DecimalLiteral(new BigDecimal(5)), new DecimalLiteral(new BigDecimal(3)));
        DecimalValue actualAdditionValue = (DecimalValue) expressionEvaluator.visitAddition(actualAddition, null);

        assertEquals(new BigDecimal(8), actualAdditionValue.getPlainValue());
    }

    @Test
    public void shouldReturnIntegerOnDivisionUsingIntegers() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        Division actualDivision = new Division(new IntegerLiteral(11), new IntegerLiteral(2));
        IntegerValue actualDivisionValue = (IntegerValue) expressionEvaluator.visitDivision(actualDivision, null);

        assertSame(5, actualDivisionValue.getPlainValue());
    }

    @Test
    public void shouldReturnIntegerWhenEncapsulatedAsGroup() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        Group actualGroup = new Group(new IntegerLiteral(3));
        IntegerValue actualIntegerValue = (IntegerValue) expressionEvaluator.visitGroup(actualGroup, null);

        assertSame(3, actualIntegerValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueFromGreaterThanUsingIntegers() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        GreaterThan greaterThan = new GreaterThan(new IntegerLiteral(5), new IntegerLiteral(8));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitGreaterThan(greaterThan, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueFromGreaterThanUsingIntegers() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        GreaterThan greaterThan = new GreaterThan(new IntegerLiteral(5), new IntegerLiteral(2));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitGreaterThan(greaterThan, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueFromGreaterThanUsingDecimals() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        GreaterThan greaterThan = new GreaterThan(new DecimalLiteral(new BigDecimal(3)), new DecimalLiteral(new BigDecimal(8)));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitGreaterThan(greaterThan, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueFromGreaterThanUsingDecimals() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        GreaterThan greaterThan = new GreaterThan(new DecimalLiteral(new BigDecimal(10)), new DecimalLiteral(new BigDecimal(8)));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitGreaterThan(greaterThan, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueFromNegation() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        Negation greaterThan = new Negation(new BooleanLiteral(false));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitNegation(greaterThan, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueFromNegation() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        Negation greaterThan = new Negation(new BooleanLiteral(true));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitNegation(greaterThan, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueFromLogicalAnd() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        LogicalAnd logicalAnd = new LogicalAnd(new BooleanLiteral(true), new BooleanLiteral(false));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitAnd(logicalAnd, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueFromLogicalAnd() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        LogicalAnd logicalAnd = new LogicalAnd(new BooleanLiteral(true), new BooleanLiteral(true));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitAnd(logicalAnd, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueFromGreaterOrEqualThanUsingIntegers() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        GreaterThanOrEqual greaterThanOrEqual = new GreaterThanOrEqual(new IntegerLiteral(5), new IntegerLiteral(8));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitGreaterThanOrEqual(greaterThanOrEqual, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueFromGreaterOrEqualThanUsingIntegers() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        GreaterThanOrEqual greaterThanOrEqual = new GreaterThanOrEqual(new IntegerLiteral(5), new IntegerLiteral(2));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitGreaterThanOrEqual(greaterThanOrEqual, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanFalseValueFromGreaterThanOrEqualUsingDecimals() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        GreaterThanOrEqual greaterThanOrEqual = new GreaterThanOrEqual(new DecimalLiteral(new BigDecimal(3)), new DecimalLiteral(new BigDecimal(8)));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitGreaterThanOrEqual(greaterThanOrEqual, null);

        assertFalse(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueFromGreaterThanOrEqualUsingDecimals() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        GreaterThanOrEqual greaterThanOrEqual = new GreaterThanOrEqual(new DecimalLiteral(new BigDecimal(10)), new DecimalLiteral(new BigDecimal(8)));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitGreaterThanOrEqual(greaterThanOrEqual, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueFromGreaterOrEqualOnEqualThanUsingIntegers() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        GreaterThanOrEqual greaterThanOrEqual = new GreaterThanOrEqual(new IntegerLiteral(5), new IntegerLiteral(5));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitGreaterThanOrEqual(greaterThanOrEqual, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }

    @Test
    public void shouldReturnBooleanTrueValueFromGreaterThanOrEqualOnEqualUsingDecimals() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

        GreaterThanOrEqual greaterThanOrEqual = new GreaterThanOrEqual(new DecimalLiteral(new BigDecimal(3)), new DecimalLiteral(new BigDecimal(3)));
        BooleanValue actualBooleanValue = (BooleanValue) expressionEvaluator.visitGreaterThanOrEqual(greaterThanOrEqual, null);

        assertTrue(actualBooleanValue.getPlainValue());
    }
/*
    @Test
    public void shouldDeclareQuestionDefaultValueInValueTable() {
        ValueTable valueTable = new ValueTable();
        ExpressionEvaluator evaluator = new ExpressionEvaluator(valueTable);
        Question question = new Question(new Identifier("example"), new QuestionLabel("example question?"),
                new BooleanType(), new Addition(new IntegerLiteral(3), new IntegerLiteral(4)));

        evaluator.visitQuestion(question, null);

        assertSame(1, valueTable.size());
        assertSame(7, valueTable.lookup(new Identifier("example")).getPlainValue());
    }

    @Test
    public void shouldCheckStatementsOnIfThenStatement() {
        ValueTable valueTable = new ValueTable();
        ExpressionEvaluator evaluator = new ExpressionEvaluator(valueTable);
        List<Statement> statements = new ArrayList<>();
        Question firstQuestion = new Question(new Identifier("firstExample"), new QuestionLabel("example question?"),
                new BooleanType(), new Addition(new IntegerLiteral(3), new IntegerLiteral(4)));
        Question secondQuestion = new Question(new Identifier("secondExample"), new QuestionLabel("example question?"),
                new BooleanType(), new Addition(new IntegerLiteral(2), new IntegerLiteral(3)));
        statements.add(firstQuestion);
        statements.add(secondQuestion);

        IfThen ifThen = new IfThen(new GreaterThan(new IntegerLiteral(3), new IntegerLiteral(5)), statements);

        evaluator.visitIfThen(ifThen, null);

        assertSame(2, valueTable.size());
        assertSame(7, valueTable.lookup(new Identifier("firstExample")).getPlainValue());
        assertSame(5, valueTable.lookup(new Identifier("secondExample")).getPlainValue());
    }

    @Test
    public void shouldCheckStatementsOnIfThenElseStatement() {
        ValueTable valueTable = new ValueTable();
        ExpressionEvaluator evaluator = new ExpressionEvaluator(valueTable);

        List<Statement> ifStatements = new ArrayList<>();
        Question firstQuestion = new Question(new Identifier("firstExample"), new QuestionLabel("example question?"),
                new BooleanType(), new Addition(new IntegerLiteral(3), new IntegerLiteral(4)));
        ifStatements.add(firstQuestion);

        List<Statement> elseStatements = new ArrayList<>();
        Question secondQuestion = new Question(new Identifier("secondExample"), new QuestionLabel("example question?"),
                new BooleanType(), new Addition(new IntegerLiteral(2), new IntegerLiteral(3)));
        elseStatements.add(secondQuestion);

        IfThenElse ifThen = new IfThenElse(new GreaterThan(new IntegerLiteral(3), new IntegerLiteral(5)), ifStatements, elseStatements);

        evaluator.visitIfThenElse(ifThen, null);

        assertSame(2, valueTable.size());
        assertSame(7, valueTable.lookup(new Identifier("firstExample")).getPlainValue());
        assertSame(5, valueTable.lookup(new Identifier("secondExample")).getPlainValue());
    }

    @Test
    public void shouldEvaluateForm() {
        ValueTable valueTable = new ValueTable();
        ExpressionEvaluator evaluator = new ExpressionEvaluator(valueTable);

        List<Statement> statements = new ArrayList<>();
        Question firstQuestion = new Question(new Identifier("example"), new QuestionLabel("example question?"),
                new BooleanType(), new Addition(new IntegerLiteral(3), new IntegerLiteral(4)));
        statements.add(firstQuestion);

        evaluator.visitForm(new Form(new Identifier("exampleForm"), statements), null);

        assertSame(1, valueTable.size());
        assertSame(7, valueTable.lookup(new Identifier("example")).getPlainValue());
    }*/
}