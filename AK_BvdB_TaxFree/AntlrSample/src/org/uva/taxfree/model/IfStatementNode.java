package org.uva.taxfree.model;

import java.util.Set;

public class IfStatementNode extends Node {
    String mCondition;
    BooleanExpressionNode mExpression;

    public IfStatementNode(String condition) {
        super();
        mCondition = new String(condition);
    }

    public void addExpression(BooleanExpressionNode condition) {
        mExpression = condition;
    }

    protected boolean evaluateCondition() {
        assert mExpression != null;
        return mExpression.evaluate();
    }

    @Override
    public void setVisibility(boolean isVisible) {
        System.out.println("I evaluate to " + evaluateCondition());
        super.setVisibility(evaluateCondition());
    }

    @Override
    protected void addCondition(Set<Node> set) {
        set.add(this);
    }

    @Override
    public String toString() {
        return mCondition;
    }

}