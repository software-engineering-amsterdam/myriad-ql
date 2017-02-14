package org.ql.ast.literal;

import org.ql.ast.Node;

public class BooleanLiteral implements Node {
    private boolean booleanLiteral;

    public BooleanLiteral(boolean booleanLiteral) {
        this.booleanLiteral = booleanLiteral;
    }

    public boolean getBooleanLiteral() {
        return booleanLiteral;
    }
}
