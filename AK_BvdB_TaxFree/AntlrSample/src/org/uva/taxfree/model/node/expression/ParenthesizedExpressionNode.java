package org.uva.taxfree.model.node.expression;

public class ParenthesizedExpressionNode extends ConditionNode {
    private final ConditionNode mCondition;

    public ParenthesizedExpressionNode(ConditionNode condition) {
        super();
        mCondition = condition;
    }

    @Override
    public String resolveValue() {
        return mCondition.resolveValue();
    }
}
