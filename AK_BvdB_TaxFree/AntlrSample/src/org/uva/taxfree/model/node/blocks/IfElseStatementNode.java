package org.uva.taxfree.model.node.blocks;

import org.uva.taxfree.model.node.Node;

import java.util.Set;

public class IfElseStatementNode extends BlockNode {
    private final IfStatementNode mIfStatementNode;

    public IfElseStatementNode(IfStatementNode ifStatement, Set<Node> children) {
        super(children);
        mIfStatementNode = ifStatement;
    }

    @Override
    public void setVisible(boolean isVisible) {
        mIfStatementNode.setVisible(isVisible);
        super.setVisible(!mIfStatementNode.isTrue() && isVisible);
    }

    @Override
    public String toString() {
        return mIfStatementNode.toString();
    }
}