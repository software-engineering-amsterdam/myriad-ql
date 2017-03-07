package org.uva.taxfree.model.node.literal;

import org.uva.taxfree.model.types.StringType;
import org.uva.taxfree.model.types.Type;

public class StringLiteralNode extends LiteralNode {
    public StringLiteralNode(String id) {
        super(id);
    }

    @Override
    public Type getType() {
        return new StringType();
    }
}
