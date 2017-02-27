package org.ql.typechecker.expression;

import org.junit.Test;
import org.ql.ast.expression.ExpressionVisitor;
import org.ql.ast.Identifier;
import org.ql.ast.expression.Parameter;
import org.ql.ast.expression.arithmetic.*;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.DecimalLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.ast.expression.relational.*;
import org.ql.ast.type.*;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ExpressionTypeCheckerTest {
    @Test(expected = TypeMismatchException.class)
    public void shouldThrowExceptionWhenNegationAppliedOnNonBoolean() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());
        Negation negation = new Negation(new StringLiteral("example string"));

        visitor.visit(negation);
    }

    @Test
    public void shouldReturnBooleanTypeWhenNegationHasABooleanLiteral() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());
        Negation negation = new Negation(new BooleanLiteral(true));

        Type actualNegationType = visitor.visit(negation);

        assertTrue(actualNegationType instanceof BooleanType);
    }

    @Test
    public void shouldReturnLiteralTypes() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());

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
    public void shouldReturnIntegerLiteralWhenIncrementAppliedOnInteger() throws Throwable {
        ExpressionTypeChecker visitor = new ExpressionTypeChecker(new HashMapSymbolTable());

        Type actualIntegerType = visitor.visit(new Increment(new IntegerLiteral(3)));

        assertTrue(actualIntegerType instanceof IntegerType);
    }

    @Test
    public void shouldReturnFloatWhenIncrementAppliedOnFloat() throws Throwable {
        ExpressionTypeChecker visitor = new ExpressionTypeChecker(new HashMapSymbolTable());

        Type actualFloatType = visitor.visit(new Increment(new DecimalLiteral(new BigDecimal(10.40))));

        assertTrue(actualFloatType instanceof FloatType);
    }

    @Test(expected = UnexpectedTypeException.class)
    public void shouldThrowUnexpectedTypeExceptionWhenIncrementAppliedOnNonIntegerOrFloat() throws Throwable {
        ExpressionTypeChecker visitor = new ExpressionTypeChecker(new HashMapSymbolTable());

        visitor.visit(new Increment(new StringLiteral("example")));
    }

    @Test
    public void shouldReturnIntegerTypeWhenDecrementAppliedOnInteger() throws Throwable {
        ExpressionTypeChecker visitor = new ExpressionTypeChecker(new HashMapSymbolTable());

        Type actualIntegerType = visitor.visit(new Decrement(new IntegerLiteral(3)));

        assertTrue(actualIntegerType instanceof IntegerType);
    }

    @Test
    public void shouldReturnFloatTypeWhenDecrementAppliedOnFloat() throws Throwable {
        ExpressionTypeChecker visitor = new ExpressionTypeChecker(new HashMapSymbolTable());

        Type actualFloatType = visitor.visit(new Decrement(new DecimalLiteral(new BigDecimal(10.40))));

        assertTrue(actualFloatType instanceof FloatType);
    }

    @Test(expected = UnexpectedTypeException.class)
    public void shouldThrowUnexpectedTypeExceptionWhenDecrementAppliedOnNonIntegerOrFloat() throws Throwable {
        ExpressionTypeChecker visitor = new ExpressionTypeChecker(new HashMapSymbolTable());

        Type actualType = visitor.visit(new Decrement(new StringLiteral("example")));

        assertTrue(actualType instanceof IntegerType);
    }

    @Test(expected = UndefinedIdentifierException.class)
    public void shouldThrowUndefinedIdentifierExceptionWhenIdentifierDoesNotExist() throws Throwable {
        ExpressionTypeChecker visitor = new ExpressionTypeChecker(new HashMapSymbolTable());

        visitor.visit(new Parameter(new Identifier("example")));
    }

    @Test
    public void shouldReturnParameterWhenIdentifierExists() throws Throwable {
        HashMapSymbolTable HashMapSymbolTable = new HashMapSymbolTable();
        HashMapSymbolTable.put(new Identifier("example"), new StringType());

        ExpressionTypeChecker visitor = new ExpressionTypeChecker(HashMapSymbolTable);

        Type actualParameterType = visitor.visit(new Parameter(new Identifier("example")));

        assertTrue(actualParameterType instanceof StringType);
    }

    @Test
    public void shouldReturnBooleanTypeForLogicalOr() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());
        LogicalOr actualLogicalOr = new LogicalOr(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualLogicalOrType = visitor.visit(actualLogicalOr);

        assertTrue(actualLogicalOrType instanceof BooleanType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesAreUsedInLogicalOr() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());
        LogicalOr actualLogicalOr = new LogicalOr(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visit(actualLogicalOr);
    }

    @Test
    public void shouldReturnBooleanTypeForLowerThanOrEquals() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());
        LowerThanOrEqual actualLowerThanOrEqual = new LowerThanOrEqual(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualLowerThanOrEqualType = visitor.visit(actualLowerThanOrEqual);

        assertTrue(actualLowerThanOrEqualType instanceof BooleanType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesAreUsedInLowerThanOrEqual() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());
        LowerThanOrEqual actualLowerThanOrEqual = new LowerThanOrEqual(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visit(actualLowerThanOrEqual);
    }

    @Test
    public void shouldReturnBooleanTypeForEquals() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());
        Equals actualEqual = new Equals(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualEqualType = visitor.visit(actualEqual);

        assertTrue(actualEqualType instanceof BooleanType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesAreUsedInEquals() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());
        Equals actualEquals = new Equals(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visit(actualEquals);
    }

    @Test
    public void shouldReturnBooleanTypeForGreaterThan() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());
        GreaterThan actualGreaterThan = new GreaterThan(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualGreaterThanType = visitor.visit(actualGreaterThan);

        assertTrue(actualGreaterThanType instanceof BooleanType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesAreUsedInGreaterThan() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());
        GreaterThan actualGreaterThan = new GreaterThan(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visit(actualGreaterThan);
    }

    @Test
    public void shouldReturnIntegerTypeForAddition() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());
        Addition actualAddition = new Addition(new IntegerLiteral(123), new IntegerLiteral(321));

        Type actualAdditionType = visitor.visit(actualAddition);

        assertTrue(actualAdditionType instanceof IntegerType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesAreUsedInAddition() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());
        Addition actualAddition = new Addition(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visit(actualAddition);
    }

    @Test
    public void shouldReturnIntegerTypeForDivision() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());
        Division actualDivision = new Division(new IntegerLiteral(123), new IntegerLiteral(321));

        Type actualDivisionType = visitor.visit(actualDivision);

        assertTrue(actualDivisionType instanceof IntegerType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesAreUsedInDivision() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());
        Division actualDivision = new Division(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visit(actualDivision);
    }

    @Test
    public void shouldReturnBooleanTypeForGreaterThanOrEqual() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());
        GreaterThanOrEqual actualGreaterThanOrEqual = new GreaterThanOrEqual(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualGreaterThanOrEqualType = visitor.visit(actualGreaterThanOrEqual);

        assertTrue(actualGreaterThanOrEqualType instanceof BooleanType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesAreUsedInGreaterThanOrEqual() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());
        GreaterThanOrEqual actualGreaterThanOrEqual = new GreaterThanOrEqual(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visit(actualGreaterThanOrEqual);
    }

    @Test
    public void shouldReturnBooleanTypeForLowerThan() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());
        LowerThan actualLowerThan = new LowerThan(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualLowerThanType = visitor.visit(actualLowerThan);

        assertTrue(actualLowerThanType instanceof BooleanType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesAreUsedInLowerThan() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());
        LowerThan actualLowerThan = new LowerThan(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visit(actualLowerThan);
    }

    @Test
    public void shouldReturnBooleanTypeForLogicalAnd() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());
        LogicalAnd actualLogicalAnd = new LogicalAnd(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualLogicalAndType = visitor.visit(actualLogicalAnd);

        assertTrue(actualLogicalAndType instanceof BooleanType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesAreUsedInLogicalAnd() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());
        LogicalAnd actualLogicalAnd = new LogicalAnd(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visit(actualLogicalAnd);
    }

    @Test
    public void shouldReturnBooleanTypeForNotEqual() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());
        NotEqual actualNotEqual = new NotEqual(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualNotEqualType = visitor.visit(actualNotEqual);

        assertTrue(actualNotEqualType instanceof BooleanType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesAreUsedInNotEqual() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());

        NotEqual actualNotEqual = new NotEqual(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visit(actualNotEqual);
    }

    @Test
    public void shouldReturnBooleanTypeOnGroupedRelationalExpression() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());
        Group actualGroup = new Group(new LogicalAnd(new BooleanLiteral(true), new BooleanLiteral(true)));

        Type actualGroupType = visitor.visit(actualGroup);

        assertTrue(actualGroupType instanceof BooleanType);
    }

    @Test
    public void shouldReturnIntegerTypeForProduct() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());
        Product actualProduct = new Product(new IntegerLiteral(123), new IntegerLiteral(321));

        Type actualProductType = visitor.visit(actualProduct);

        assertTrue(actualProductType instanceof IntegerType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesAreUsedInProduct() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());
        Product actualProduct = new Product(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visit(actualProduct);
    }

    @Test
    public void shouldReturnWhenIntegerTypeForSubtraction() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());

        Type actualSubtractionType = visitor.visit(new Subtraction(new IntegerLiteral(12), new IntegerLiteral(4)));

        assertTrue(actualSubtractionType instanceof IntegerType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesUsedForSubtraction() throws Throwable {
        ExpressionVisitor<Type> visitor = new ExpressionTypeChecker(new HashMapSymbolTable());

        visitor.visit(new Subtraction(new IntegerLiteral(12), new StringLiteral("example")));
    }
}
