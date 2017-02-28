package org.uva.taxfree.model.node.expression;

import org.uva.taxfree.model.node.condition.ConditionNode;

public class BooleanExpressionNode extends ExpressionNode {
    public BooleanExpressionNode(ConditionNode left, String operator, ConditionNode right) {
        super(left, operator, right);
    }
}
