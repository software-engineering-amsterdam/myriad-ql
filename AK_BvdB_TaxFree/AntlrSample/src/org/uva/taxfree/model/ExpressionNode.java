package org.uva.taxfree.model;

public class ExpressionNode extends Node {
    private Node mLeft;
    private Node mRight;

    public ExpressionNode(String label) {

    }

    public boolean evaluate() {
        return true;
    }

    public String getType() {
        return mLeft.getType();
    }

    public boolean isValid() {
        return mLeft.getType().equals(mRight.getType());
    }
}
