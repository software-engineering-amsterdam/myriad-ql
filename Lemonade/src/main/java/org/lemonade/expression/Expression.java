package org.lemonade.expression;

import org.lemonade.QLOperatorType;
import org.lemonade.QLType;

public abstract class Expression {
    private QLType type;
    private QLOperatorType operator;

    private int left;
    public Expression (QLType type, int value) {
        this.type = type;
        this.left = value;
    }

    public int getLeft() {
        return left;
    }

    public QLOperatorType getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return Integer.toString(this.left);
    }
}
