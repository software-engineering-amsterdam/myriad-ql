package org.lemonade.expression;

import org.lemonade.QLOperatorType;

public abstract class UnaryExpression extends Expression {
    private Expression expression;

    public UnaryExpression() {
        super();
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

}