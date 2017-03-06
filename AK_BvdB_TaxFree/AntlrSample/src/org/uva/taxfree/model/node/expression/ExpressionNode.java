package org.uva.taxfree.model.node.expression;

import java.util.List;

public abstract class ExpressionNode extends ConditionNode {
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

    @Override
    public void addUsedVariables(List<String> list) {
        mLeft.addUsedVariables(list);
        mRight.addUsedVariables(list);
    }
}
