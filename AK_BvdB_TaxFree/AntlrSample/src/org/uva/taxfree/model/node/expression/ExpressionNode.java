package org.uva.taxfree.model.node.expression;

import java.util.Set;

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
    public void addUsedVariables(Set<String> set) {
        mLeft.addUsedVariables(set);
        mRight.addUsedVariables(set);
    }
}
