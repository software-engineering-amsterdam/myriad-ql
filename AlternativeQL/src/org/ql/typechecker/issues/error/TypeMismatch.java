package org.ql.typechecker.issues.error;

import org.ql.ast.type.Type;
import org.ql.typechecker.issues.Issue;

public class TypeMismatch implements Issue {
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
