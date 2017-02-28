package org.uva.taxfree.model.node.condition;

import org.uva.taxfree.model.node.Node;

public class IfElseStatementNode extends Node {
    private IfStatementNode mIfStatementNode;

    public IfElseStatementNode() {
        super();
    }

    @Override
    public void addChild(Node node) {
        if (mIfStatementNode == null) {
            setIfStatementNode((IfStatementNode) node);
        } else {
            super.addChild(node);
        }
    }

    public void setIfStatementNode(IfStatementNode ifStatementNode) {
        mIfStatementNode = ifStatementNode;
    }

    @Override
    public void setVisibility(boolean isVisible) {
        mIfStatementNode.setVisibility(isVisible);
        super.setVisibility(!mIfStatementNode.isTrue() && isVisible);
    }

    @Override
    public String toString() {
        return mIfStatementNode.toString();
    }
}