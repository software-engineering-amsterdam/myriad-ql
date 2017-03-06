package org.ql.typechecker.issues.error;

import org.ql.ast.type.Type;
import org.ql.typechecker.issues.Issue;

public class NumberExpected implements Issue {
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
