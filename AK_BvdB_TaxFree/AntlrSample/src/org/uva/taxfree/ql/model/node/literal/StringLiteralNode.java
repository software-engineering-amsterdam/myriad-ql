package org.uva.taxfree.ql.model.node.literal;

import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.types.StringType;
import org.uva.taxfree.ql.model.types.Type;
import org.uva.taxfree.ql.model.values.StringValue;
import org.uva.taxfree.ql.model.values.Value;

public class StringLiteralNode extends LiteralNode {
    private final String mValue;

    public StringLiteralNode(String constantValue, SourceInfo sourceInfo) {
        super(sourceInfo);
        mValue = constantValue;
    }

    @Override
    public Value evaluate() {
        return new StringValue(mValue);
    }

    @Override
    public Type getType() {
        return new StringType();
    }
}
