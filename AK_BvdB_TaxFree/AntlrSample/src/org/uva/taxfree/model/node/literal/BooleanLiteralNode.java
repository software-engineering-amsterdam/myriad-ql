package org.uva.taxfree.model.node.literal;

import org.uva.taxfree.model.types.BooleanType;
import org.uva.taxfree.model.types.Type;

public class BooleanLiteralNode extends LiteralNode {
    private boolean mValue;

    public BooleanLiteralNode(boolean constantValue) {
        mValue = constantValue;
    }


    @Override
    protected boolean asBoolean() {
        return mValue;
    }

    @Override
    protected int asInteger() {
        return mValue ? 1 : 0;
    }

    @Override
    protected String asString() {
        return String.valueOf(mValue);
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }
}



