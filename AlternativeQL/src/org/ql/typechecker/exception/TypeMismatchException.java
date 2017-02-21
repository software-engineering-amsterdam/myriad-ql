package org.ql.typechecker.exception;

import org.ql.ast.type.Type;

public class TypeMismatchException extends Throwable implements TypeError {
    private final Type expected;
    private final Type actual;

    public TypeMismatchException(Type expected, Type actual) {
        this.expected = expected;
        this.actual = actual;
    }
}
