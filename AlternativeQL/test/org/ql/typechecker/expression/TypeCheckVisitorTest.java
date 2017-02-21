package org.ql.typechecker.expression;

import org.junit.Test;
import org.ql.ast.expression.Visitor;
import org.ql.ast.Identifier;
import org.ql.ast.expression.Parameter;
import org.ql.ast.expression.arithmetic.Decrement;
import org.ql.ast.expression.arithmetic.Increment;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.DecimalLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.ast.expression.relational.Equals;
import org.ql.ast.expression.relational.LogicalOr;
import org.ql.ast.expression.relational.LowerThanOrEqual;
import org.ql.ast.expression.relational.Negation;
import org.ql.ast.type.*;
import org.ql.typechecker.exception.TypeMismatchException;
import org.ql.typechecker.exception.UndefinedIdentifierException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TypeCheckVisitorTest {
    @Test(expected = TypeMismatchException.class)
    public void shouldThrowExceptionWhenNegationAppliedOnNonBoolean() throws Throwable {
        Visitor<Type> visitor = new TypeCheckVisitor(new HashMap<>());
        Negation negation = new Negation(new StringLiteral("example string"));

        visitor.visit(negation);
    }

    @Test
    public void shouldReturnBooleanTypeWhenNegationHasABooleanLiteral() throws Throwable {
        Visitor<Type> visitor = new TypeCheckVisitor(new HashMap<>());
        Negation negation = new Negation(new BooleanLiteral(true));

        Type actualNegationType = visitor.visit(negation);

        assertTrue(actualNegationType instanceof BooleanType);
    }

    @Test
    public void shouldReturnLiteralTypes() throws Throwable {
        Visitor<Type> visitor = new TypeCheckVisitor(new HashMap<>());

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
    public void shouldNotThrowTypeMismatchErrorWhenIncrementAppliedOnInteger() {
        TypeCheckVisitor visitor = new TypeCheckVisitor(new HashMap<>());
        Type actualIntegerType = visitor.visit(new Increment(new IntegerLiteral(3)));
        assertTrue(actualIntegerType instanceof IntegerType);
    }

    @Test
    public void shouldNotThrowTypeMismatchErrorWhenIncrementAppliedOnFloat() {
        TypeCheckVisitor visitor = new TypeCheckVisitor(new HashMap<>());
        Type actualFloatType = visitor.visit(new Increment(new DecimalLiteral(new BigDecimal(10.40))));
        assertTrue(actualFloatType instanceof FloatType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchErrorWhenIncrementAppliedOnNonIntegerOrFloat() {
        TypeCheckVisitor visitor = new TypeCheckVisitor(new HashMap<>());
        Type actualType = visitor.visit(new Increment(new StringLiteral("example")));
        assertTrue(actualType instanceof IntegerType);
    }

    @Test
    public void shouldNotThrowTypeMismatchErrorWhenDecrementAppliedOnInteger() {
        TypeCheckVisitor visitor = new TypeCheckVisitor(new HashMap<>());
        Type actualIntegerType = visitor.visit(new Decrement(new IntegerLiteral(3)));
        assertTrue(actualIntegerType instanceof IntegerType);
    }

    @Test
    public void shouldNotThrowTypeMismatchErrorWhenDecrementAppliedOnFloat() {
        TypeCheckVisitor visitor = new TypeCheckVisitor(new HashMap<>());
        Type actualFloatType = visitor.visit(new Decrement(new DecimalLiteral(new BigDecimal(10.40))));
        assertTrue(actualFloatType instanceof FloatType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchErrorWhenDecrementAppliedOnNonIntegerOrFloat() {
        TypeCheckVisitor visitor = new TypeCheckVisitor(new HashMap<>());
        Type actualType = visitor.visit(new Decrement(new StringLiteral("example")));
        assertTrue(actualType instanceof IntegerType);
    }

    @Test(expected = UndefinedIdentifierException.class)
    public void shouldThrowUndefinedIdentifierExceptionWhenIdentifierDoesNotExist() {
        TypeCheckVisitor visitor = new TypeCheckVisitor(new HashMap<>());
        visitor.visit(new Parameter(new Identifier("example")));
    }

    @Test
    public void shouldReturnParameterWhenIdentifierExists() {
        Map<Identifier, Type> definitions = new HashMap<>();
        definitions.put(new Identifier("example"), new StringType());
        TypeCheckVisitor visitor = new TypeCheckVisitor(definitions);
        visitor.visit(new Parameter(new Identifier("example")));

    }

    @Test
    public void shouldReturnBooleanTypeForLogicalOr() throws Throwable {
        Visitor<Type> visitor = new TypeCheckVisitor(new HashMap<>());
        LogicalOr actualLogicalOr = new LogicalOr(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualLogicalOrType = visitor.visit(actualLogicalOr);

        assertTrue(actualLogicalOrType instanceof BooleanType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesAreUsedInLogicalOr() throws Throwable {
        Visitor<Type> visitor = new TypeCheckVisitor(new HashMap<>());
        LogicalOr actualLogicalOr = new LogicalOr(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visit(actualLogicalOr);
    }

    @Test
    public void shouldReturnBooleanTypeForLowerThanOrEquals() throws Throwable {
        Visitor<Type> visitor = new TypeCheckVisitor(new HashMap<>());
        LowerThanOrEqual actualLowerThanOrEqual = new LowerThanOrEqual(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualLowerThanOrEqualType = visitor.visit(actualLowerThanOrEqual);

        assertTrue(actualLowerThanOrEqualType instanceof BooleanType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesAreUsedInLowerThanOrEqual() throws Throwable {
        Visitor<Type> visitor = new TypeCheckVisitor(new HashMap<>());
        LowerThanOrEqual actualLowerThanOrEqual = new LowerThanOrEqual(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visit(actualLowerThanOrEqual);
    }

    @Test
    public void shouldReturnBooleanTypeForEquals() throws Throwable {
        Visitor<Type> visitor = new TypeCheckVisitor(new HashMap<>());
        Equals actualEqual = new Equals(new BooleanLiteral(true), new BooleanLiteral(false));

        Type actualEqualType = visitor.visit(actualEqual);

        assertTrue(actualEqualType instanceof BooleanType);
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenDifferentTypesAreUsedInEquals() throws Throwable {
        Visitor<Type> visitor = new TypeCheckVisitor(new HashMap<>());
        Equals actualEquals = new Equals(new BooleanLiteral(true), new IntegerLiteral(12));

        visitor.visit(actualEquals);
    }
}
