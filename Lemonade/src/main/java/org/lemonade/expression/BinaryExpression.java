package org.lemonade.expression;

import org.lemonade.QLOperatorType;
import org.lemonade.QLType;

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

}
