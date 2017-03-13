package org.uva.taxfree.model.node.blocks;

import org.uva.taxfree.model.node.Node;

import java.util.Set;

public class IfElseStatementNode extends BlockNode {
    private final BlockNode mIfStatementNode;

    public IfElseStatementNode(BlockNode ifStatement, Set<Node> children) {
        super(children);
        mIfStatementNode = ifStatement;
    }


    @Override
    public String toString() {
        return mIfStatementNode.toString();
    }
}