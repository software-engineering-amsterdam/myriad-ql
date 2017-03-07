package org.uva.taxfree.model.node.expression;

import org.uva.taxfree.model.types.Type;

import java.util.Set;

public abstract class BinaryExpressionNode extends ExpressionNode {
    private final ExpressionNode mLeft;
    private final String mOperator;
    private final ExpressionNode mRight;

    public BinaryExpressionNode(ExpressionNode right, String operator, ExpressionNode left) {
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

    @Override
    public boolean isValid() {
        return mLeft.isSameType(mRight);
    }

    @Override
    public Type getType() {
        if (!isValid()) {
            throw new AssertionError("Either side works since the expression isn't valid anyway.");
        }
        return mLeft.getType();
    }
}
