package org.ql.typechecker.expression;

import org.junit.Test;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.DecimalLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.ast.expression.relational.Negation;
import org.ql.ast.type.*;
import org.ql.typechecker.exception.TypeMismatchException;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.Assert.*;

public class TypeCheckVisitorTest {
    @Test(expected = TypeMismatchException.class)
    public void shouldThrowExceptionWhenNegationAppliedOnNonBoolean() {
        TypeCheckVisitor visitor = new TypeCheckVisitor(new HashMap<>());
        Negation negation = new Negation(new StringLiteral("example string"));

        visitor.visit(negation);
    }

    @Test
    public void shouldReturnBooleanTypeWhenNegationHasABooleanLiteral() {
        TypeCheckVisitor visitor = new TypeCheckVisitor(new HashMap<>());
        Negation negation = new Negation(new BooleanLiteral(true));

        Type actualNegationType = visitor.visit(negation);

        assertTrue(actualNegationType instanceof BooleanType);
    }

    @Test
    public void shouldReturnLiteralTypes() {
        TypeCheckVisitor visitor = new TypeCheckVisitor(new HashMap<>());
        Type actualStringType = visitor.visit(new StringLiteral("example"));
        Type actualFloatType = visitor.visit(new DecimalLiteral(new BigDecimal(4.5)));
        Type actualBooleanType = visitor.visit(new BooleanLiteral(false));
        Type actualIntegerType = visitor.visit(new IntegerLiteral(34));

        assertTrue(actualStringType instanceof StringType);
        assertTrue(actualFloatType instanceof FloatType);
        assertTrue(actualBooleanType instanceof BooleanType);
        assertTrue(actualIntegerType instanceof IntegerType);
    }
}
