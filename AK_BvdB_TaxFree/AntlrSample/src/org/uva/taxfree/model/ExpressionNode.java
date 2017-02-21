package org.uva.taxfree.model;

public class ExpressionNode extends Node {
    private Node mLeft;
    private Node mRight;
    private String mOperator;

    public ExpressionNode(String label) {

    }

    public boolean evaluate() {
        return true;
    }

    public String getType() {
        return mLeft.getType();
    }

    @Override
    public String getId() {
        return mLeft.getId() + mOperator + mRight.getId();
    }

    public boolean isValid() {
        return mLeft.getType().equals(mRight.getType());
    }
}
