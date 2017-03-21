package org.uva.taxfree.ql.model.node.literal;

import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.types.IntegerType;
import org.uva.taxfree.ql.model.types.Type;
import org.uva.taxfree.ql.model.values.IntValue;
import org.uva.taxfree.ql.model.values.Value;

public class IntegerLiteralNode extends LiteralNode {
    private final int mValue;

    public IntegerLiteralNode(int constantValue, SourceInfo sourceInfo) {
        super(sourceInfo);
        mValue = constantValue;
    }

    @Override
    public Value evaluate() {
        return new IntValue(mValue);
    }

    @Override
    public Type getType() {
        return new IntegerType();
    }
}
