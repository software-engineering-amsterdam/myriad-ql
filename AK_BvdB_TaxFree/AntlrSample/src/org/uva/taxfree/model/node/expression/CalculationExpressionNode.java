package org.uva.taxfree.model.node.expression;

public class CalculationExpressionNode extends ExpressionNode {
    public CalculationExpressionNode(ConditionNode left, String operator, ConditionNode right) {
        super(left, operator, right);
    }
}
