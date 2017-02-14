package org.ql.ast.literal;

import org.ql.ast.Node;

public class IntegerLiteral implements Node {
    private int integerLiteral;

    public IntegerLiteral(int integerLiteral) {
        this.integerLiteral = integerLiteral;
    }

    public int getIntegerLiteral() {
        return integerLiteral;
    }
}
