package org.uva.taxfree.model.node.expression;

import org.uva.taxfree.model.node.condition.ConditionNode;

public class UniformExpressionNode extends ExpressionNode {
    public UniformExpressionNode(ConditionNode left, String operator, ConditionNode right) {
        super(left, operator, right);
    }
}
