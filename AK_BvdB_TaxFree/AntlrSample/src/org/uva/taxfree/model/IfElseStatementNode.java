package org.uva.taxfree.model;

public class IfElseStatementNode extends Node {
    IfStatementNode mIfNode;

    public IfElseStatementNode(String label) {
        super();
    }

    public void registerCondition(IfStatementNode condition) {
        mIfNode = condition;
    }

    // setVisibility =>
}