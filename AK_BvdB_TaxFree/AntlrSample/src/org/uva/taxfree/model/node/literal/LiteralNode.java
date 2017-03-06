package org.uva.taxfree.model.node.literal;

import org.uva.taxfree.model.node.expression.ConditionNode;

import java.util.List;

public abstract class LiteralNode extends ConditionNode {
    private final String mId;

    public LiteralNode(String id) {
        super();
        mId = id;
    }

    public String resolveValue() {
        return mId;
    }

    @Override
    public void addUsedVariables(List<String> list) {
        list.add(mId);
    }
}
