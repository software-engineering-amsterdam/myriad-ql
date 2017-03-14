package org.uva.taxfree.model.node.literal;

import org.uva.taxfree.model.types.BooleanType;
import org.uva.taxfree.model.types.Type;

public class BooleanLiteralNode extends LiteralNode {
    public BooleanLiteralNode(String id) {
        super(id);
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }
}



