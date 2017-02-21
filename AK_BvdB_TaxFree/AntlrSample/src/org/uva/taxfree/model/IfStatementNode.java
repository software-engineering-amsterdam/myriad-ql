package org.uva.taxfree.model;

import java.util.Set;

public class IfStatementNode extends Node {
    String mExpression;
    ConditionNode mCondition;


    public IfStatementNode(String expression) {
        super();
        mExpression = expression;
    }

    @Override
    public void addChild(Node node) {
        if (mCondition == null) {
            setCondition((ConditionNode) node);
        } else {
            super.addChild(node);
        }
    }

    public void setCondition(ConditionNode condition) {
        mCondition = condition;
    }

    protected boolean isTrue() {
        assert mCondition != null;
        return ("true" == mCondition.evaluate());
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
        return mExpression;
    }

}