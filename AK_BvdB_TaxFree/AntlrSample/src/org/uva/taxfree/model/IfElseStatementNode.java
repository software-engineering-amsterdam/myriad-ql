package org.uva.taxfree.model;

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
    public String toString() {
        return mIfStatementNode.toString();
    }
}