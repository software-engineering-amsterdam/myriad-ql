package org.uva.taxfree.model;

import java.util.Set;

public class IfStatementNode extends Node {
    String mCondition;
    ExpressionNode mExpression;

    public IfStatementNode(String condition) {
        super();
        mCondition = new String(condition);
    }

    @Override
    public void addChild(Node node) {
        if (mExpression == null) {
            setCondition((ExpressionNode)node);
        } else {
            super.addChild(node);
        }
    }

    public void setCondition(ExpressionNode condition) {
        mExpression = condition;
    }

    protected boolean isTrue() {
        assert mExpression != null;
        return ("true" == mExpression.evaluate());
    }

    @Override
    public void setVisibility(boolean isVisible) {
        System.out.println("I evaluate to " + isTrue());
        super.setVisibility(isTrue());
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