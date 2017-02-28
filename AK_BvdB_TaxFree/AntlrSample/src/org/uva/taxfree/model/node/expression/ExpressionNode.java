package org.uva.taxfree.model.node.expression;

import org.uva.taxfree.model.node.condition.ConditionNode;

public class ExpressionNode extends ConditionNode {
    private final ConditionNode mLeft;
    private final String mOperator;
    private final ConditionNode mRight;

    public ExpressionNode(ConditionNode left, String operator, ConditionNode right) {
        mLeft = left;
        mOperator = operator;
        mRight = right;
    }

    @Override
    public String resolveValue() {
        return "(" + mLeft.resolveValue() + mOperator + mRight.resolveValue() + ")";
    }

}
