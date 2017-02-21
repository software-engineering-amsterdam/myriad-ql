package org.uva.taxfree.model;

public class ExpressionNode extends ConditionNode {
    private Node mLeft;
    private String mOperator;
    private Node mRight;

    public ExpressionNode(String operator) {
        mOperator = operator;
    }

    @Override
    public void addChild(Node node) {
        if (mLeft == null) {
            mLeft = node;
        } else if (mRight == null) {
            mRight = node;
        } else {
            // Error handling!
        }
    }

    @Override
    public String toString() {
        return "(" + mLeft.toString() + mOperator + mRight.toString() + ")";
    }

}
