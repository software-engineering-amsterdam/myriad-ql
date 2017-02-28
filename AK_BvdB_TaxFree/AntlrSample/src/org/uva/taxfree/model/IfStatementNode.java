package org.uva.taxfree.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class IfStatementNode extends Node {
    private ConditionNode mCondition;

    public IfStatementNode() {
        super();
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
        super.setVisibility(isTrue() && isVisible);
    }

    @Override
    protected void addCondition(Set<Node> set) {
        set.add(mCondition);
    }

    @Override
    protected Set<ConditionNode> addConditions() {
        Set<ConditionNode> nodeSet = new LinkedHashSet<>();
        nodeSet.add(mCondition);
        return nodeSet;
    }

    @Override
    public String toString() {
        return mCondition.toString();
    }
}