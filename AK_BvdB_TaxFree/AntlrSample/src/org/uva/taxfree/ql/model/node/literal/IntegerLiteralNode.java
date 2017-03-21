package org.uva.taxfree.ql.model.node.literal;

import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.types.IntegerType;
import org.uva.taxfree.ql.model.types.Type;

public class IntegerLiteralNode extends LiteralNode {
    private final int mValue;

    public IntegerLiteralNode(int constantValue, SourceInfo sourceInfo) {
        super(sourceInfo);
        mValue = constantValue;
    }

    @Override
    public Type getType() {
        return new IntegerType();
    }

    @Override
    public boolean asBoolean() {
        return 0 != mValue;
    }

    @Override
    public int asInteger() {
        return mValue;
    }

    @Override
    public String asString() {
        return String.valueOf(mValue);
    }
}
