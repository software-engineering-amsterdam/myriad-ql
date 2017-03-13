package org.uva.taxfree.model.node.blocks;

import org.uva.taxfree.model.node.Node;
import org.uva.taxfree.model.node.expression.ExpressionNode;

import java.util.List;

public class IfElseStatementNode extends IfStatementNode {
    private final List<Node> mElseChildren;

    public IfElseStatementNode(ExpressionNode expressionNode, List<Node> thenStatementNodes, List<Node> elseStatementNodes) {
        super(expressionNode, thenStatementNodes);
        mElseChildren = elseStatementNodes;
    }
}