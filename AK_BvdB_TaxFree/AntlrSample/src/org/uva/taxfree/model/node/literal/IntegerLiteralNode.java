package org.uva.taxfree.model.node.literal;

import org.uva.taxfree.model.types.IntegerType;
import org.uva.taxfree.model.types.Type;

public class IntegerLiteralNode extends LiteralNode {
    public IntegerLiteralNode(String id) {
        super(id);
    }

    @Override
    public Type getType() {
        return new IntegerType();
    }
}
