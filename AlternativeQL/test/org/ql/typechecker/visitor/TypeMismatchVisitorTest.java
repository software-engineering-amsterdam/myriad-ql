package org.ql.typechecker.visitor;

import org.junit.Test;
import org.ql.ast.identifier.Identifier;
import org.ql.ast.expression.Parameter;
import org.ql.ast.expression.arithmetic.*;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.DecimalLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.ast.expression.relational.*;
import org.ql.ast.statement.ComputableQuestion;
import org.ql.ast.statement.question.QuestionLabel;
import org.ql.ast.type.*;
import org.ql.typechecker.SymbolTable;
import org.ql.typechecker.issues.IssuesStorage;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class TypeMismatchVisitorTest {
    @Test
    public void shouldNotGiveErrorWhenQuestionTypeIsMoneyAndValueTypeIsFloat() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);

        visitor.visitComputableQuestion(new ComputableQuestion(new Identifier("test"), new QuestionLabel("example question?"), new MoneyType(), new DecimalLiteral(new BigDecimal(23.4))), null);

        assertEquals(0, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldGiveErrorWhenQuestionTypeIsMoneyAndValueTypeIsNonDecimal() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);

        visitor.visitComputableQuestion(new ComputableQuestion(new Identifier("test"), new QuestionLabel("example question?"), new MoneyType(), new BooleanLiteral(true)), null);

        assertEquals(1, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldThrowExceptionWhenNegationAppliedOnNonBoolean() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);
        Negation negation = new Negation(new StringLiteral("example string"));

        Type actualNegationType = visitor.visitNegation(negation, null);

        assertTrue(actualNegationType instanceof UnknownType);
        assertEquals("Type mismatch: expected boolean, but got string", issuesStorage.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnBooleanTypeWhenNegationHasABooleanLiteral() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);
        Negation negation = new Negation(new BooleanLiteral(true));

        Type actualNegationType = visitor.visitNegation(negation, null);

        assertTrue(actualNegationType instanceof BooleanType);
        assertEquals(0, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldReturnLiteralTypes() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);

        Type actualStringType = visitor.visitString(new StringLiteral("example"), null);
        Type actualFloatType = visitor.visitDecimal(new DecimalLiteral(new BigDecimal(4.5)), null);
        Type actualBooleanType = visitor.visitBoolean(new BooleanLiteral(false), null);
        Type actualIntegerType = visitor.visitInteger(new IntegerLiteral(34), null);

        assertNotNull(actualStringType);
        assertNotNull(actualFloatType);
        assertNotNull(actualBooleanType);
        assertNotNull(actualIntegerType);
        assertEquals(0, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldReturnIntegerLiteralWhenIncrementAppliedOnInteger() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);

        Type actualIntegerType = visitor.visitIncrement(new Increment(new IntegerLiteral(3)), null);

        assertTrue(actualIntegerType instanceof IntegerType);
        assertEquals(0, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldReturnFloatWhenIncrementAppliedOnFloat() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);

        Type actualFloatType = visitor.visitIncrement(new Increment(new DecimalLiteral(new BigDecimal(10.40))), null);

        assertTrue(actualFloatType instanceof FloatType);
        assertEquals(0, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldReturnUnknownTypeWhenIncrementAppliedOnNonIntegerOrFloat() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);

        Type actualIncrementType = visitor.visitIncrement(new Increment(new StringLiteral("example")), null);

        assertTrue(actualIncrementType instanceof UnknownType);
        assertEquals(1, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldReturnIntegerTypeWhenDecrementAppliedOnInteger() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);

        Type actualIntegerType = visitor.visitDecrement(new Decrement(new IntegerLiteral(3)), null);

        assertTrue(actualIntegerType instanceof IntegerType);
        assertEquals(0, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldReturnFloatTypeWhenDecrementAppliedOnFloat() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);

        Type actualFloatType = visitor.visitDecrement(new Decrement(new DecimalLiteral(new BigDecimal(10.40))), null);

        assertTrue(actualFloatType instanceof FloatType);
        assertEquals(0, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldReturnUnknownTypeWhenDecrementAppliedOnNonIntegerOrFloat() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);

        Type actualType = visitor.visitDecrement(new Decrement(new StringLiteral("example")), null);

        assertTrue(actualType instanceof UnknownType);
        assertEquals(1, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldReturnParameterWhenIdentifierExists() {
        SymbolTable symbolTable = new SymbolTable();
        symbolTable.declare(new Identifier("example"), new StringType());

        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);

        Type actualParameterType = visitor.visitParameter(new Parameter(new Identifier("example")), symbolTable);

        assertTrue(actualParameterType instanceof StringType);
        assertEquals(0, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeForLogicalOr() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);
        LogicalOr actualLogicalOr = new LogicalOr(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualLogicalOrType = visitor.visitOr(actualLogicalOr, null);

        assertNotNull(actualLogicalOrType);
        assertEquals(0, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInLogicalOr() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);
        LogicalOr actualLogicalOr = new LogicalOr(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visitOr(actualLogicalOr, null);

        assertEquals(1, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorNonBooleanTypesAreUsedInLogicalOr() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);
        LogicalOr actualLogicalOr = new LogicalOr(new IntegerLiteral(233), new IntegerLiteral(12));

        visitor.visitOr(actualLogicalOr, null);

        assertEquals(1, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeForLowerThanOrEquals() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);
        LowerThanOrEqual actualLowerThanOrEqual = new LowerThanOrEqual(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualLowerThanOrEqualType = visitor.visitLowerThanOrEqual(actualLowerThanOrEqual, null);

        assertNotNull(actualLowerThanOrEqualType);
        assertEquals(0, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInLowerThanOrEqual() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);
        LowerThanOrEqual actualLowerThanOrEqual = new LowerThanOrEqual(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visitLowerThanOrEqual(actualLowerThanOrEqual, null);

        assertEquals(1, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeForEquals() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);
        Equals actualEqual = new Equals(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualEqualType = visitor.visitEquals(actualEqual, null);

        assertNotNull(actualEqualType);
        assertEquals(0, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInEquals() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);
        Equals actualEquals = new Equals(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visitEquals(actualEquals, null);
        assertEquals(1, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeForGreaterThan() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);
        GreaterThan actualGreaterThan = new GreaterThan(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualGreaterThanType = visitor.visitGreaterThan(actualGreaterThan, null);

        assertNotNull(actualGreaterThanType);
        assertEquals(0, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInGreaterThan() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);
        GreaterThan actualGreaterThan = new GreaterThan(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visitGreaterThan(actualGreaterThan, null);
        assertEquals(1, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldReturnIntegerTypeForAddition() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);
        Addition actualAddition = new Addition(new IntegerLiteral(123), new IntegerLiteral(321));

        Type actualAdditionType = visitor.visitAddition(actualAddition, null);

        assertTrue(actualAdditionType instanceof IntegerType);
        assertEquals(0, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInAddition() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);
        Addition actualAddition = new Addition(new BooleanLiteral(true), new IntegerLiteral(12));

        Type actualAdditionType = visitor.visitAddition(actualAddition, null);

        assertTrue(actualAdditionType instanceof UnknownType);
        assertEquals(1, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldReturnIntegerTypeForDivision() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);
        Division actualDivision = new Division(new IntegerLiteral(123), new IntegerLiteral(321));

        Type actualDivisionType = visitor.visitDivision(actualDivision, null);

        assertTrue(actualDivisionType instanceof IntegerType);
        assertEquals(0, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInDivision() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);
        Division actualDivision = new Division(new BooleanLiteral(true), new IntegerLiteral(12));

        Type actualDivisionType = visitor.visitDivision(actualDivision, null);

        assertTrue(actualDivisionType instanceof UnknownType);
        assertEquals(1, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeForGreaterThanOrEqual() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);
        GreaterThanOrEqual actualGreaterThanOrEqual = new GreaterThanOrEqual(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualGreaterThanOrEqualType = visitor.visitGreaterThanOrEqual(actualGreaterThanOrEqual, null);

        assertNotNull(actualGreaterThanOrEqualType);
        assertEquals(0, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInGreaterThanOrEqual() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);
        GreaterThanOrEqual actualGreaterThanOrEqual = new GreaterThanOrEqual(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visitGreaterThanOrEqual(actualGreaterThanOrEqual, null);
        assertEquals(1, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeForLowerThan() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);
        LowerThan actualLowerThan = new LowerThan(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualLowerThanType = visitor.visitLowerThan(actualLowerThan, null);

        assertNotNull(actualLowerThanType);
        assertEquals(0, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInLowerThan() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);
        LowerThan actualLowerThan = new LowerThan(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visitLowerThan(actualLowerThan, null);
        assertEquals(1, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeForLogicalAnd() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);
        LogicalAnd actualLogicalAnd = new LogicalAnd(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualLogicalAndType = visitor.visitAnd(actualLogicalAnd, null);

        assertNotNull(actualLogicalAndType);
        assertEquals(0, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInLogicalAnd() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);
        LogicalAnd actualLogicalAnd = new LogicalAnd(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visitAnd(actualLogicalAnd, null);
        assertEquals(1, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeForNotEqual() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);
        NotEqual actualNotEqual = new NotEqual(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualNotEqualType = visitor.visitNotEqual(actualNotEqual, null);

        assertNotNull(actualNotEqualType);
        assertEquals(0, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInNotEqual() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);

        NotEqual actualNotEqual = new NotEqual(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visitNotEqual(actualNotEqual, null);
        assertEquals(1, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldReturnBooleanTypeOnGroupedRelationalExpression() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);
        Group actualGroup = new Group(new LogicalAnd(new BooleanLiteral(true), new BooleanLiteral(true)));

        Type actualGroupType = visitor.visitGroup(actualGroup, null);

        assertTrue(actualGroupType instanceof BooleanType);
        assertEquals(0, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldReturnIntegerTypeForProduct() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);
        Product actualProduct = new Product(new IntegerLiteral(123), new IntegerLiteral(321));

        Type actualProductType = visitor.visitProduct(actualProduct, null);

        assertTrue(actualProductType instanceof IntegerType);
        assertEquals(0, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesAreUsedInProduct() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);
        Product actualProduct = new Product(new BooleanLiteral(true), new IntegerLiteral(12));

        Type actualProductType = visitor.visitProduct(actualProduct, null);

        assertTrue(actualProductType instanceof UnknownType);
        assertEquals(1, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldReturnWhenIntegerTypeForSubtraction() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);

        Type actualSubtractionType = visitor.visitSubtraction(new Subtraction(new IntegerLiteral(12), new IntegerLiteral(4)), null);

        assertTrue(actualSubtractionType instanceof IntegerType);
        assertEquals(0, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldGiveTypeMismatchErrorWhenDifferentTypesUsedForSubtraction() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);
        Subtraction actualSubtraction = new Subtraction(new IntegerLiteral(12), new StringLiteral("example"));

        Type actualSubtractionType = visitor.visitSubtraction(actualSubtraction, null);

        assertTrue(actualSubtractionType instanceof UnknownType);
        assertEquals(1, issuesStorage.getErrors().size());
    }

    @Test
    public void shouldReturnUnknownTypeWhenIdentifierDoesNotExist() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);

        Type actualParameterType = visitor.visitParameter(new Parameter(new Identifier("example")), new SymbolTable());

        assertTrue(actualParameterType instanceof UnknownType);
    }

    @Test
    public void shouldGiveUndefinedSymbolErrorWhenIdentifierDoesNotExist() {
        IssuesStorage issuesStorage = new IssuesStorage();
        TypeMismatchVisitor visitor = new TypeMismatchVisitor(issuesStorage);

        visitor.visitParameter(new Parameter(new Identifier("example")), new SymbolTable());

        assertEquals(1, issuesStorage.getErrors().size());
    }

}