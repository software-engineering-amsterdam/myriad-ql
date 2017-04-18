package org.uva.taxfree.ql.model.node.literal;

import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.types.BooleanType;
import org.uva.taxfree.ql.model.types.Type;
import org.uva.taxfree.ql.model.values.BooleanValue;
import org.uva.taxfree.ql.model.values.Value;

public class BooleanLiteralNode extends LiteralNode {
    private boolean mValue;

    public BooleanLiteralNode(boolean constantValue, SourceInfo sourceInfo) {
        super(sourceInfo);
        mValue = constantValue;
    }

    @Override
    public Value evaluate() {
        return new BooleanValue(mValue);
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }
}



