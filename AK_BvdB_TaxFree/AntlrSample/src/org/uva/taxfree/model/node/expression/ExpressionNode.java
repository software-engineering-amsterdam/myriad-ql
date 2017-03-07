package org.uva.taxfree.model.node.expression;

import java.util.Set;

public abstract class ExpressionNode extends ConditionNode {
    private final ConditionNode mLeft;
    private final String mOperator;
    private final ConditionNode mRight;

    public ExpressionNode(ConditionNode right, String operator, ConditionNode left) {
        mRight = right;
        mOperator = operator;
        mLeft = left;
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

//    @Override
    public boolean isSameType() {
        return mLeft.getClass().equals(mRight.getClass());
    }
}
