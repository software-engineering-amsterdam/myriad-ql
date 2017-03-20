package org.uva.taxfree.ql.model.node.literal;

import org.uva.taxfree.ql.model.types.StringType;
import org.uva.taxfree.ql.model.types.Type;

public class StringLiteralNode extends LiteralNode {
    private final String mValue;

    public StringLiteralNode(String constantValue) {
        mValue = constantValue;
    }

    @Override
    public boolean asBoolean() {
        return Boolean.valueOf(mValue);
    }

    @Override
    public int asInteger() {
        return Integer.valueOf(mValue);
    }

    @Override
    public String asString() {
        return mValue;
    }

    @Override
    public Type getType() {
        return new StringType();
    }
}
