package org.ql.typechecker.expression;

import org.junit.Test;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.ast.expression.relational.Negation;
import org.ql.ast.type.Type;
import org.ql.typechecker.messages.BooleanExpected;

import java.util.HashMap;

import static org.junit.Assert.*;

public class TypeCheckVisitorTest {
    @Test
    public void shouldAddErrorWhenNegationAppliedOnNonBoolean() {
        TypeCheckVisitor visitor = new TypeCheckVisitor(new HashMap<>());
        Negation negation = new Negation(new StringLiteral("example string"));

        visitor.visit(negation);

        assertTrue(visitor.getErrors().get(0) instanceof BooleanExpected);
    }

    @Test
    public void shouldReturnNullWhenNegationAppliedOnNonBoolean() {
        TypeCheckVisitor visitor = new TypeCheckVisitor(new HashMap<>());
        Negation negation = new Negation(new StringLiteral("example string"));
        Type expectedType = null;

        Type actualType = visitor.visit(negation);

        assertSame(expectedType, actualType);
    }
}