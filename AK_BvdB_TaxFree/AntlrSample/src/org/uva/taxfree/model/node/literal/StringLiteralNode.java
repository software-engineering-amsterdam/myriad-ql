package org.uva.taxfree.model.node.literal;

import org.uva.taxfree.model.types.StringType;
import org.uva.taxfree.model.types.Type;

public class StringLiteralNode extends LiteralNode {
    private final String mValue;

    public StringLiteralNode(String constantValue) {
        mValue = constantValue;
    }

    @Override
    protected boolean asBoolean() {
        return Boolean.valueOf(mValue);
    }

    @Override
    protected int asInteger() {
        return Integer.valueOf(mValue);
    }

    @Override
    protected String asString() {
        return mValue;
    }

    @Override
    public Type getType() {
        return new StringType();
    }
}
