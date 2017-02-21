package org.uva.taxfree.model;

public class ExpressionNode extends Node {
    private Node mLeft;
    private String mOperator;
    private Node mRight;

    public ExpressionNode(String label) {

    }

    public String getType() {
        return mLeft.getType();
    }

    @Override
    public String toString() {
        return "(" + mLeft.toString() + mOperator + mRight.toString() + ")";
    }

    public boolean isValid() {
        return mLeft.getType().equals(mRight.getType());
    }

}