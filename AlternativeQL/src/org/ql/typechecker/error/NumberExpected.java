package org.ql.typechecker.error;

import org.ql.ast.type.Type;

public class NumberExpected implements TypeError {
    private final Type innerExpressionType;

    public NumberExpected(Type innerExpressionType) {
        this.innerExpressionType = innerExpressionType;
    }

    @Override
    public Type getNode() {
        return innerExpressionType;
    }

    @Override
    public String getMessage() {
        return "Expected number type, got " + innerExpressionType;
    }
}
