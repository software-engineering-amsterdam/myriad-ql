package org.lemonade.nodes.expressions;

public abstract class UnaryExpression extends Expression {

    private Expression expression;

    public UnaryExpression(Expression expr) {
        this.expression = expr;
    }

    public Expression getExpression() {
        return expression;
    }

}
