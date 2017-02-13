package org.ql.ast.literal;

import org.ql.ast.Node;

public class StringLiteral implements Node {
    private String stringLiteral;

    public StringLiteral(String stringLiteral) {
        this.stringLiteral = stringLiteral;
    }

    public String toString() {
        return stringLiteral;
    }
}
