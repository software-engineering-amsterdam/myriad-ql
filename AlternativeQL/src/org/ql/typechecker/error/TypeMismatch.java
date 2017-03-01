package org.ql.typechecker.error;

import org.ql.ast.Node;
import org.ql.ast.type.Type;

public class TypeMismatch implements TypeError {
    private final Type expected;
    private final Type actual;

    public TypeMismatch(Type expected, Type actual) {
        this.expected = expected;
        this.actual = actual;
    }

    @Override
    public Type getNode() {
        return actual;
    }

    @Override
    public String getMessage() {
        return "Type mismatch: expected " + expected + ", but got " + actual;
    }
}
