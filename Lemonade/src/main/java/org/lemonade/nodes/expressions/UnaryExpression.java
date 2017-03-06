package org.lemonade.nodes.expressions;

import org.lemonade.nodes.Position;
import org.lemonade.visitors.ASTVisitor;

public abstract class UnaryExpression extends Expression {
    private Expression expression;

    public UnaryExpression(Expression expr) {
        this.expression = expr;
    }

    public Expression getExpression() {
        return expression;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}