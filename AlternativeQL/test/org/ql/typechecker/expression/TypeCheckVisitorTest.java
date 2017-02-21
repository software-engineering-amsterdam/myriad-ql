package org.ql.typechecker.expression;

import org.junit.Test;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.ast.expression.relational.Negation;
import org.ql.ast.type.BooleanType;
import org.ql.ast.type.Type;
import org.ql.typechecker.exception.TypeMismatchException;
import org.ql.typechecker.messages.BooleanExpected;

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

        Type actual = visitor.visit(negation);

        assertTrue(actual instanceof BooleanType);
    }
}
