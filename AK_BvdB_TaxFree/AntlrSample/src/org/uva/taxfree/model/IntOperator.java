package org.uva.taxfree.model;

import javax.swing.*;

public abstract class IntOperator extends OperatorNode {
    private NumberNode mLeft;
    private NumberNode mRight;

    public IntOperator(String description, String id, NumberNode left, NumberNode right) {
        super(description, id);
        mLeft = left;
        mRight = right;
    }

    public boolean isValid() {
        return (mLeft.getType().equals(mRight.getType()));
    }

    protected abstract String evaluate(NumberNode mLeft, NumberNode mRight);

    abstract boolean isEqual(Node otherNode);
}
