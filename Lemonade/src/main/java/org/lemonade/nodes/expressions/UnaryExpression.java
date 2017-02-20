package org.lemonade.nodes.expressions;

import org.lemonade.visitors.ASTVisitor;

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

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}