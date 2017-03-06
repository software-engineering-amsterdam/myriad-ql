package org.uva.taxfree.model.node.expression;

import java.util.List;

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
    public void addUsedVariables(List<String> list) {
        mCondition.addUsedVariables(list);
    }
}
