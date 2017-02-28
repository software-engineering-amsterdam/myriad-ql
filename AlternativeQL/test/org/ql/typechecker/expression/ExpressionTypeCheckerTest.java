package org.ql.typechecker.expression;

import org.junit.Test;
import org.ql.ast.Identifier;
import org.ql.ast.expression.ExpressionVisitor;
import org.ql.ast.expression.Parameter;
import org.ql.ast.expression.arithmetic.*;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.DecimalLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.ast.expression.relational.*;
import org.ql.ast.type.*;
import org.ql.symbol_table.SymbolTable;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class ExpressionTypeCheckerTest {
    @Test(expected = TypeMismatchException.class)
    public void shouldThrowExceptionWhenNegationAppliedOnNonBoolean() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());
        Negation negation = new Negation(new StringLiteral("example string"));

        visitor.visit(negation);
    }

    @Test
    public void shouldReturnBooleanTypeWhenNegationHasABooleanLiteral() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());
        Negation negation = new Negation(new BooleanLiteral(true));

        Type actualNegationType = visitor.visit(negation);

        assertTrue(actualNegationType instanceof BooleanType);
    }

    @Test
    public void shouldReturnLiteralTypes() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());

        Type actualStringType = visitor.visit(new StringLiteral("example"));
        Type actualFloatType = visitor.visit(new DecimalLiteral(new BigDecimal(4.5)));
        Type actualBooleanType = visitor.visit(new BooleanLiteral(false));
        Type actualIntegerType = visitor.visit(new IntegerLiteral(34));

        assertTrue(actualStringType instanceof StringType);
        assertTrue(actualFloatType instanceof FloatType);
        assertTrue(actualBooleanType instanceof BooleanType);
        assertTrue(actualIntegerType instanceof IntegerType);
    }

    @Test
    public void shouldReturnIntegerLiteralWhenIncrementAppliedOnInteger() {
        ExpressionTypeChecker visitor = new ExpressionTypeChecker(new SymbolTable());

        Type actualIntegerType = visitor.visit(new Increment(new IntegerLiteral(3)));

        assertTrue(actualIntegerType instanceof IntegerType);
    }

    @Test
    public void shouldReturnFloatWhenIncrementAppliedOnFloat() {
        ExpressionTypeChecker visitor = new ExpressionTypeChecker(new SymbolTable());

        Type actualFloatType = visitor.visit(new Increment(new DecimalLiteral(new BigDecimal(10.40))));

        assertTrue(actualFloatType instanceof FloatType);
    }

    @Test(expected = NumberExpectedException.class)
    public void shouldThrowUnexpectedTypeExceptionWhenIncrementAppliedOnNonIntegerOrFloat() {
        ExpressionTypeChecker visitor = new ExpressionTypeChecker(new SymbolTable());

        visitor.visit(new Increment(new StringLiteral("example")));
    }

    @Test
    public void shouldReturnIntegerTypeWhenDecrementAppliedOnInteger() {
        ExpressionTypeChecker visitor = new ExpressionTypeChecker(new SymbolTable());

        Type actualIntegerType = visitor.visit(new Decrement(new IntegerLiteral(3)));

        assertTrue(actualIntegerType instanceof IntegerType);
    }

    @Test
    public void shouldReturnFloatTypeWhenDecrementAppliedOnFloat() {
        ExpressionTypeChecker visitor = new ExpressionTypeChecker(new SymbolTable());

        Type actualFloatType = visitor.visit(new Decrement(new DecimalLiteral(new BigDecimal(10.40))));

        assertTrue(actualFloatType instanceof FloatType);
    }

    @Test(expected = NumberExpectedException.class)
    public void shouldThrowUnexpectedTypeExceptionWhenDecrementAppliedOnNonIntegerOrFloat() {
        ExpressionTypeChecker visitor = new ExpressionTypeChecker(new SymbolTable());

        Type actualType = visitor.visit(new Decrement(new StringLiteral("example")));

        assertTrue(actualType instanceof IntegerType);
    }

    @Test(expected = UndefinedIdentifierException.class)
    public void shouldThrowUndefinedIdentifierExceptionWhenIdentifierDoesNotExist() {
        ExpressionTypeChecker visitor = new ExpressionTypeChecker(new SymbolTable());

        visitor.visit(new Parameter(new Identifier("example")));
    }

    @Test
    public void shouldReturnParameterWhenIdentifierExists() {
        SymbolTable SymbolTable = new SymbolTable();
        SymbolTable.declare(new Identifier("example"), new StringType());

        ExpressionTypeChecker visitor = new ExpressionTypeChecker(SymbolTable);

        Type actualParameterType = visitor.visit(new Parameter(new Identifier("example")));

        assertTrue(actualParameterType instanceof StringType);
    }

    @Test
    public void shouldReturnBooleanTypeForLogicalOr() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());
        LogicalOr actualLogicalOr = new LogicalOr(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualLogicalOrType = visitor.visit(actualLogicalOr);

        assertTrue(actualLogicalOrType instanceof BooleanType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesAreUsedInLogicalOr() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());
        LogicalOr actualLogicalOr = new LogicalOr(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visit(actualLogicalOr);
    }

    @Test
    public void shouldReturnBooleanTypeForLowerThanOrEquals() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());
        LowerThanOrEqual actualLowerThanOrEqual = new LowerThanOrEqual(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualLowerThanOrEqualType = visitor.visit(actualLowerThanOrEqual);

        assertTrue(actualLowerThanOrEqualType instanceof BooleanType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesAreUsedInLowerThanOrEqual() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());
        LowerThanOrEqual actualLowerThanOrEqual = new LowerThanOrEqual(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visit(actualLowerThanOrEqual);
    }

    @Test
    public void shouldReturnBooleanTypeForEquals() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());
        Equals actualEqual = new Equals(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualEqualType = visitor.visit(actualEqual);

        assertTrue(actualEqualType instanceof BooleanType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesAreUsedInEquals() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());
        Equals actualEquals = new Equals(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visit(actualEquals);
    }

    @Test
    public void shouldReturnBooleanTypeForGreaterThan() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());
        GreaterThan actualGreaterThan = new GreaterThan(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualGreaterThanType = visitor.visit(actualGreaterThan);

        assertTrue(actualGreaterThanType instanceof BooleanType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesAreUsedInGreaterThan() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());
        GreaterThan actualGreaterThan = new GreaterThan(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visit(actualGreaterThan);
    }

    @Test
    public void shouldReturnIntegerTypeForAddition() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());
        Addition actualAddition = new Addition(new IntegerLiteral(123), new IntegerLiteral(321));

        Type actualAdditionType = visitor.visit(actualAddition);

        assertTrue(actualAdditionType instanceof IntegerType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesAreUsedInAddition() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());
        Addition actualAddition = new Addition(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visit(actualAddition);
    }

    @Test
    public void shouldReturnIntegerTypeForDivision() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());
        Division actualDivision = new Division(new IntegerLiteral(123), new IntegerLiteral(321));

        Type actualDivisionType = visitor.visit(actualDivision);

        assertTrue(actualDivisionType instanceof IntegerType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesAreUsedInDivision() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());
        Division actualDivision = new Division(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visit(actualDivision);
    }

    @Test
    public void shouldReturnBooleanTypeForGreaterThanOrEqual() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());
        GreaterThanOrEqual actualGreaterThanOrEqual = new GreaterThanOrEqual(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualGreaterThanOrEqualType = visitor.visit(actualGreaterThanOrEqual);

        assertTrue(actualGreaterThanOrEqualType instanceof BooleanType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesAreUsedInGreaterThanOrEqual() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());
        GreaterThanOrEqual actualGreaterThanOrEqual = new GreaterThanOrEqual(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visit(actualGreaterThanOrEqual);
    }

    @Test
    public void shouldReturnBooleanTypeForLowerThan() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());
        LowerThan actualLowerThan = new LowerThan(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualLowerThanType = visitor.visit(actualLowerThan);

        assertTrue(actualLowerThanType instanceof BooleanType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesAreUsedInLowerThan() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());
        LowerThan actualLowerThan = new LowerThan(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visit(actualLowerThan);
    }

    @Test
    public void shouldReturnBooleanTypeForLogicalAnd() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());
        LogicalAnd actualLogicalAnd = new LogicalAnd(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualLogicalAndType = visitor.visit(actualLogicalAnd);

        assertTrue(actualLogicalAndType instanceof BooleanType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesAreUsedInLogicalAnd() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());
        LogicalAnd actualLogicalAnd = new LogicalAnd(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visit(actualLogicalAnd);
    }

    @Test
    public void shouldReturnBooleanTypeForNotEqual() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());
        NotEqual actualNotEqual = new NotEqual(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualNotEqualType = visitor.visit(actualNotEqual);

        assertTrue(actualNotEqualType instanceof BooleanType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesAreUsedInNotEqual() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());

        NotEqual actualNotEqual = new NotEqual(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visit(actualNotEqual);
    }

    @Test
    public void shouldReturnBooleanTypeOnGroupedRelationalExpression() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());
        Group actualGroup = new Group(new LogicalAnd(new BooleanLiteral(true), new BooleanLiteral(true)));

        Type actualGroupType = visitor.visit(actualGroup);

        assertTrue(actualGroupType instanceof BooleanType);
    }

    @Test
    public void shouldReturnIntegerTypeForProduct() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());
        Product actualProduct = new Product(new IntegerLiteral(123), new IntegerLiteral(321));

        Type actualProductType = visitor.visit(actualProduct);

        assertTrue(actualProductType instanceof IntegerType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesAreUsedInProduct() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());
        Product actualProduct = new Product(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visit(actualProduct);
    }

    @Test
    public void shouldReturnWhenIntegerTypeForSubtraction() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());

        Type actualSubtractionType = visitor.visit(new Subtraction(new IntegerLiteral(12), new IntegerLiteral(4)));

        assertTrue(actualSubtractionType instanceof IntegerType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesUsedForSubtraction() {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new SymbolTable());

        visitor.visit(new Subtraction(new IntegerLiteral(12), new StringLiteral("example")));
    }
}
