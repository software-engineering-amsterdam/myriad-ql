package org.uva.taxfree.model.node.literal;

import org.uva.taxfree.model.node.condition.ConditionNode;

public abstract class LiteralNode extends ConditionNode {
    private final String mId;

    public LiteralNode(String id) {
        super();
        mId = id;
    }

    public String resolveValue() {
        return mId;
    }

}
