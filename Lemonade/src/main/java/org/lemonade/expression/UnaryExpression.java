package org.lemonade.expression;

import org.lemonade.QLOperatorType;

public abstract class UnaryExpression extends Expression {
    private Expression expression;

    public UnaryExpression() {

    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

}