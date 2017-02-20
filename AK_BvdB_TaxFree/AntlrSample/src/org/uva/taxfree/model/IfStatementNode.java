package org.uva.taxfree.model;

public class IfStatementNode extends Node {
    String mCondition;
    ExpressionNode mExpression;

    public IfStatementNode(String condition) {
        super();
        mCondition = new String(condition);
    }

    public void addExpression(ExpressionNode child) {
        mExpression = child;
    }

    protected boolean evaluateCondition() {
        assert mExpression != null;
        return mExpression.evaluate();
    }

}