package org.lemonade.nodes.expressions;

import org.lemonade.visitors.ExpressionVisitor;

public abstract class BinaryExpression extends Expression {
    private Expression left;
    private Expression right;

    public BinaryExpression() {
        super();
    }

    public void setLeft(Expression left) {
        this.left = left;
    }

    public void setRight(Expression right) {
        this.right = right;
    }

    public Expression getLeft() {
        return this.left;
    }

    public Expression getRight() {
        return this.right;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor){
        return visitor.visit(this);
    }
}
