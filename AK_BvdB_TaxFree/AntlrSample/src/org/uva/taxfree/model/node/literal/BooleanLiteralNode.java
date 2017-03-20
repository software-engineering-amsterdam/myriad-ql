package org.uva.taxfree.model.node.literal;

import org.uva.taxfree.model.types.BooleanType;
import org.uva.taxfree.model.types.Type;

public class BooleanLiteralNode extends LiteralNode {
    private boolean mValue;

    public BooleanLiteralNode(boolean constantValue) {
        mValue = constantValue;
    }


    @Override
    public boolean asBoolean() {
        return mValue;
    }

    @Override
    public int asInteger() {
        return mValue ? 1 : 0;
    }

    @Override
    public String asString() {
        return String.valueOf(mValue);
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }
}



