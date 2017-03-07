package org.uva.taxfree.model.node.literal;

public class BooleanLiteralNode extends LiteralNode {
    public BooleanLiteralNode(String id) {
        super(id);
    }

    @Override
    public boolean isValid() {
        return true;
    }
}



