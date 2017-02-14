package org.ql.ast.expression.arithmetic;

import org.ql.ast.Expression;

public class Decrement implements Expression {
    private final Expression expression;

    public Decrement(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
}
