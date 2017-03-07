package org.uva.taxfree.model.node.blocks;

import org.uva.taxfree.model.node.Node;
import org.uva.taxfree.model.node.expression.ExpressionNode;

import java.util.Set;

public class IfStatementNode extends BlockNode {
    private final ExpressionNode mCondition;

    public IfStatementNode(ExpressionNode condition, Set<Node> children) {
        super(children);
        mCondition = condition;
    }

    @Override
    protected boolean isVisible() {
        return ("true".equals(mCondition.evaluate()));
    }

    @Override
    public void setVisible(boolean isVisible) {
        super.setVisible(isVisible() && isVisible);
    }

    @Override
    public void addCondition(Set<ExpressionNode> set) {
        set.add(mCondition);
    }
}