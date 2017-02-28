package org.uva.taxfree.model.node.expression;

import org.uva.taxfree.model.node.condition.ConditionNode;

public class CalculationExpressionNode extends ExpressionNode {
    public CalculationExpressionNode(ConditionNode left, String operator, ConditionNode right) {
        super(left, operator, right);
    }
}
