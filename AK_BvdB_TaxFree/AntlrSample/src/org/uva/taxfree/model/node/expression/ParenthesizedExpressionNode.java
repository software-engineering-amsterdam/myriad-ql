package org.uva.taxfree.model.node.expression;

import java.util.Set;

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

    @Override
    public void addUsedVariables(Set<String> set) {
        mCondition.addUsedVariables(set);
    }
}
