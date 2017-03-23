package org.ql.ast.expression;

public abstract class BinaryExpression extends Expression {
    private final Expression left;
    private final Expression right;

    public BinaryExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }
}
