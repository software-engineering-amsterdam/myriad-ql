package org.uva.taxfree.model.node.expression;

public class BooleanExpressionNode extends ExpressionNode {
    public BooleanExpressionNode(ConditionNode left, String operator, ConditionNode right) {
        super(left, operator, right);
    }

}
