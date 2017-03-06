package org.ql.typechecker.visitor;

import org.junit.Test;
import org.ql.ast.Identifier;
import org.ql.ast.expression.Parameter;
import org.ql.ast.expression.arithmetic.*;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.DecimalLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.ast.expression.relational.*;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.question.QuestionText;
import org.ql.ast.type.*;
import org.ql.typechecker.SymbolTable;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class TypeMismatchVisitorTest {
    @Test
    public void shouldNotGiveErrorWhenQuestionTypeIsMoneyAndValueTypeIsFloat() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());

        visitor.visitQuestion(new Question(new Identifier("test"), new QuestionText("example question?"), new MoneyType(), new DecimalLiteral(new BigDecimal(23.4))), null);

        assertEquals(0, visitor.getErrors().size());
    }

    @Test
    public void shouldGiveErrorWhenQuestionTypeIsMoneyAndValueTypeIsNonDecimal() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());

        visitor.visitQuestion(new Question(new Identifier("test"), new QuestionText("example question?"), new MoneyType(), new BooleanLiteral(true)), null);

        assertEquals(1, visitor.getErrors().size());
    }

    @Test
    public void shouldThrowExceptionWhenNegationAppliedOnNonBoolean() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());
        Negation negation = new Negation(new StringLiteral("example string"));

        Type actualNegationType = visitor.visitNegation(negation, null);

        assertTrue(actualNegationType instanceof UnknownType);
        assertEquals("Type mismatch: expected boolean, but got string", visitor.getErrors().get(0));
    }

    @Test
    public void shouldReturnBooleanTypeWhenNegationHasABooleanLiteral() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());
        Negation negation = new Negation(new BooleanLiteral(true));

        Type actualNegationType = visitor.visitNegation(negation, null);

        assertTrue(actualNegationType instanceof BooleanType);
        assertEquals(0, visitor.getErrors().size());
    }

    @Test
    public void shouldReturnLiteralTypes() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());

        Type actualStringType = visitor.visitString(new StringLiteral("example"), null);
        Type actualFloatType = visitor.visitDecimal(new DecimalLiteral(new BigDecimal(4.5)), null);
        Type actualBooleanType = visitor.visitBoolean(new BooleanLiteral(false), null);
        Type actualIntegerType = visitor.visitInteger(new IntegerLiteral(34), null);

        assertNotNull(actualStringType);
        assertNotNull(actualFloatType);
        assertNotNull(actualBooleanType);
        assertNotNull(actualIntegerType);
        assertEquals(0, visitor.getErrors().size());
    }

    @Test
    public void shouldReturnIntegerLiteralWhenIncrementAppliedOnInteger() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());

        Type actualIntegerType = visitor.visitIncrement(new Increment(new IntegerLiteral(3)), null);

        assertTrue(actualIntegerType instanceof IntegerType);
        assertEquals(0, visitor.getErrors().size());
    }

    @Test
    public void shouldReturnFloatWhenIncrementAppliedOnFloat() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());

        Type actualFloatType = visitor.visitIncrement(new Increment(new DecimalLiteral(new BigDecimal(10.40))), null);

        assertTrue(actualFloatType instanceof FloatType);
        assertEquals(0, visitor.getErrors().size());
    }

    @Test
    public void shouldReturnUnknownTypeWhenIncrementAppliedOnNonIntegerOrFloat() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());

        Type actualIncrementType = visitor.visitIncrement(new Increment(new StringLiteral("example")), null);

        assertTrue(actualIncrementType instanceof UnknownType);
        assertEquals(1, visitor.getErrors().size());
    }

    @Test
    public void shouldReturnIntegerTypeWhenDecrementAppliedOnInteger() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());

        Type actualIntegerType = visitor.visitDecrement(new Decrement(new IntegerLiteral(3)), null);

        assertTrue(actualIntegerType instanceof IntegerType);
        assertEquals(0, visitor.getErrors().size());
    }

    @Test
    public void shouldReturnFloatTypeWhenDecrementAppliedOnFloat() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());

        Type actualFloatType = visitor.visitDecrement(new Decrement(new DecimalLiteral(new BigDecimal(10.40))), null);

        assertTrue(actualFloatType instanceof FloatType);
        assertEquals(0, visitor.getErrors().size());
    }

    @Test
    public void shouldReturnUnknownTypeWhenDecrementAppliedOnNonIntegerOrFloat() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());

        Type actualType = visitor.visitDecrement(new Decrement(new StringLiteral("example")), null);

        assertTrue(actualType instanceof UnknownType);
        assertEquals(1, visitor.getErrors().size());
    }

    @Test
    public void shouldReturnParameterWhenIdentifierExists() {
        SymbolTable symbolTable = new SymbolTable();
        symbolTable.declare(new Identifier("example"), new StringType());

        TypeMismatchVisitor visitor = new TypeMismatchVisitor(symbolTable);

        Type actualParameterType = visitor.visitParameter(new Parameter(new Identifier("example")), null);

        assertTrue(actualParameterType instanceof StringType);
        assertEquals(0, visitor.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeForLogicalOr() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());
        LogicalOr actualLogicalOr = new LogicalOr(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualLogicalOrType = visitor.visitOr(actualLogicalOr, null);

        assertNotNull(actualLogicalOrType);
        assertEquals(0, visitor.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInLogicalOr() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());
        LogicalOr actualLogicalOr = new LogicalOr(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visitOr(actualLogicalOr, null);

        assertEquals(1, visitor.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorNonBooleanTypesAreUsedInLogicalOr() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());
        LogicalOr actualLogicalOr = new LogicalOr(new IntegerLiteral(233), new IntegerLiteral(12));

        visitor.visitOr(actualLogicalOr, null);

        assertEquals(1, visitor.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeForLowerThanOrEquals() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());
        LowerThanOrEqual actualLowerThanOrEqual = new LowerThanOrEqual(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualLowerThanOrEqualType = visitor.visitLowerThanOrEqual(actualLowerThanOrEqual, null);

        assertNotNull(actualLowerThanOrEqualType);
        assertEquals(0, visitor.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInLowerThanOrEqual() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());
        LowerThanOrEqual actualLowerThanOrEqual = new LowerThanOrEqual(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visitLowerThanOrEqual(actualLowerThanOrEqual, null);

        assertEquals(1, visitor.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeForEquals() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());
        Equals actualEqual = new Equals(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualEqualType = visitor.visitEquals(actualEqual, null);

        assertNotNull(actualEqualType);
        assertEquals(0, visitor.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInEquals() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());
        Equals actualEquals = new Equals(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visitEquals(actualEquals, null);
        assertEquals(1, visitor.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeForGreaterThan() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());
        GreaterThan actualGreaterThan = new GreaterThan(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualGreaterThanType = visitor.visitGreaterThan(actualGreaterThan, null);

        assertNotNull(actualGreaterThanType);
        assertEquals(0, visitor.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInGreaterThan() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());
        GreaterThan actualGreaterThan = new GreaterThan(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visitGreaterThan(actualGreaterThan, null);
        assertEquals(1, visitor.getErrors().size());
    }

    @Test
    public void shouldReturnIntegerTypeForAddition() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());
        Addition actualAddition = new Addition(new IntegerLiteral(123), new IntegerLiteral(321));

        Type actualAdditionType = visitor.visitAddition(actualAddition, null);

        assertTrue(actualAdditionType instanceof IntegerType);
        assertEquals(0, visitor.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInAddition() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());
        Addition actualAddition = new Addition(new BooleanLiteral(true), new IntegerLiteral(12));

        Type actualAdditionType = visitor.visitAddition(actualAddition, null);

        assertTrue(actualAdditionType instanceof UnknownType);
        assertEquals(1, visitor.getErrors().size());
    }

    @Test
    public void shouldReturnIntegerTypeForDivision() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());
        Division actualDivision = new Division(new IntegerLiteral(123), new IntegerLiteral(321));

        Type actualDivisionType = visitor.visitDivision(actualDivision, null);

        assertTrue(actualDivisionType instanceof IntegerType);
        assertEquals(0, visitor.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInDivision() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());
        Division actualDivision = new Division(new BooleanLiteral(true), new IntegerLiteral(12));

        Type actualDivisionType = visitor.visitDivision(actualDivision, null);

        assertTrue(actualDivisionType instanceof UnknownType);
        assertEquals(1, visitor.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeForGreaterThanOrEqual() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());
        GreaterThanOrEqual actualGreaterThanOrEqual = new GreaterThanOrEqual(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualGreaterThanOrEqualType = visitor.visitGreaterThanOrEqual(actualGreaterThanOrEqual, null);

        assertNotNull(actualGreaterThanOrEqualType);
        assertEquals(0, visitor.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInGreaterThanOrEqual() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());
        GreaterThanOrEqual actualGreaterThanOrEqual = new GreaterThanOrEqual(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visitGreaterThanOrEqual(actualGreaterThanOrEqual, null);
        assertEquals(1, visitor.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeForLowerThan() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());
        LowerThan actualLowerThan = new LowerThan(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualLowerThanType = visitor.visitLowerThan(actualLowerThan, null);

        assertNotNull(actualLowerThanType);
        assertEquals(0, visitor.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInLowerThan() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());
        LowerThan actualLowerThan = new LowerThan(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visitLowerThan(actualLowerThan, null);
        assertEquals(1, visitor.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeForLogicalAnd() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());
        LogicalAnd actualLogicalAnd = new LogicalAnd(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualLogicalAndType = visitor.visitAnd(actualLogicalAnd, null);

        assertNotNull(actualLogicalAndType);
        assertEquals(0, visitor.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInLogicalAnd() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());
        LogicalAnd actualLogicalAnd = new LogicalAnd(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visitAnd(actualLogicalAnd, null);
        assertEquals(1, visitor.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeForNotEqual() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());
        NotEqual actualNotEqual = new NotEqual(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualNotEqualType = visitor.visitNotEqual(actualNotEqual, null);

        assertNotNull(actualNotEqualType);
        assertEquals(0, visitor.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInNotEqual() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());

        NotEqual actualNotEqual = new NotEqual(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visitNotEqual(actualNotEqual, null);
        assertEquals(1, visitor.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeOnGroupedRelationalExpression() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());
        Group actualGroup = new Group(new LogicalAnd(new BooleanLiteral(true), new BooleanLiteral(true)));

        Type actualGroupType = visitor.visitGroup(actualGroup, null);

        assertTrue(actualGroupType instanceof BooleanType);
        assertEquals(0, visitor.getErrors().size());
    }

    @Test
    public void shouldReturnIntegerTypeForProduct() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());
        Product actualProduct = new Product(new IntegerLiteral(123), new IntegerLiteral(321));

        Type actualProductType = visitor.visitProduct(actualProduct, null);

        assertTrue(actualProductType instanceof IntegerType);
        assertEquals(0, visitor.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInProduct() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());
        Product actualProduct = new Product(new BooleanLiteral(true), new IntegerLiteral(12));

        Type actualProductType = visitor.visitProduct(actualProduct, null);

        assertTrue(actualProductType instanceof UnknownType);
        assertEquals(1, visitor.getErrors().size());
    }

    @Test
    public void shouldReturnWhenIntegerTypeForSubtraction() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());

        Type actualSubtractionType = visitor.visitSubtraction(new Subtraction(new IntegerLiteral(12), new IntegerLiteral(4)), null);

        assertTrue(actualSubtractionType instanceof IntegerType);
        assertEquals(0, visitor.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesUsedForSubtraction() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());
        Subtraction actualSubtraction = new Subtraction(new IntegerLiteral(12), new StringLiteral("example"));

        Type actualSubtractionType = visitor.visitSubtraction(actualSubtraction, null);

        assertTrue(actualSubtractionType instanceof UnknownType);
        assertEquals(1, visitor.getErrors().size());
    }

    @Test
    public void shouldReturnUnknownTypeWhenIdentifierDoesNotExist() {
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(new SymbolTable());

        Type actualParameterType = visitor.visitParameter(new Parameter(new Identifier("example")), null);

        assertTrue(actualParameterType instanceof UnknownType);
    }

}