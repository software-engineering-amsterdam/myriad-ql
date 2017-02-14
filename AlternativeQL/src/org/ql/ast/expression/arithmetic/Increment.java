package org.ql.ast.expression.arithmetic;

import org.ql.ast.Expression;

public class Increment implements Expression {
    private final Expression expression;

    public Increment(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
}
