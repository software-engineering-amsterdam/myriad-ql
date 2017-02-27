package org.ql.typechecker.expression;

import org.ql.ast.Node;
import org.ql.ast.type.Type;

public class NumberExpectedException extends Throwable implements TypeError {
    private final Type innerExpressionType;

    public NumberExpectedException(Type innerExpressionType) {
        this.innerExpressionType = innerExpressionType;
    }

    @Override
    public Node getNode() {
        return innerExpressionType;
    }

    @Override
    public String getMessage() {
        return "Expected number type, got " + innerExpressionType;
    }
}
