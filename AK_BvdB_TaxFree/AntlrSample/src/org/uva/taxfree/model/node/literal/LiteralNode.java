package org.uva.taxfree.model.node.literal;

import org.uva.taxfree.model.node.expression.ExpressionNode;

import java.util.Set;

public abstract class LiteralNode extends ExpressionNode {
    private final String mId;

    public LiteralNode(String id) {
        super();
        mId = id;
    }

    public String resolveValue() {
        return mId;
    }

    @Override
    public void addUsedVariables(Set<String> set) {
        set.add(mId);
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
