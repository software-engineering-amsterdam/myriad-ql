package org.lemonade.nodes.expressions;

import org.lemonade.visitors.ASTVisitor;

public abstract class UnaryExpression extends Expression {
    private Expression expression;

    public UnaryExpression(Expression expr) {
        super();
        this.expression = expr;
    }

    public Expression getExpression() {
        return expression;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}