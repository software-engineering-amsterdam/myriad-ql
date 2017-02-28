package org.uva.taxfree.model.node.blocks;

import org.uva.taxfree.model.node.Node;
import org.uva.taxfree.model.node.expression.ConditionNode;

import java.util.Set;

public class IfStatementNode extends BlockNode {
    private final ConditionNode mCondition;

    public IfStatementNode(ConditionNode condition, Set<Node> children) {
        super(children);
        mCondition = condition;
    }

    @Override
    protected boolean isVisible() {
        return ("true".equals(mCondition.evaluate()));
    }

    @Override
    public void setVisible(boolean isVisible) {
        System.out.println("I evaluate to " + isVisible());
        super.setVisible(isVisible() && isVisible);
    }

    @Override
    public void addCondition(Set<Node> set) {
        set.add(mCondition);
    }

    @Override
    public String toString() {
        return mCondition.toString();
    }
}