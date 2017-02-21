package org.ql.typechecker.exception;

import org.ql.ast.type.Type;

public class UnexpectedTypeException extends Throwable implements TypeError {
    private final Type innerExpressionType;

    public UnexpectedTypeException(Type innerExpressionType) {
        this.innerExpressionType = innerExpressionType;
    }
}
