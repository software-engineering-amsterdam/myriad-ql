package org.ql.ast.expression.literal;

import org.ql.ast.Expression;

public class String implements Expression {
    private java.lang.String stringLiteral;

    public String(java.lang.String stringLiteral) {
        this.stringLiteral = stringLiteral;
    }

    public java.lang.String toString() {
        return stringLiteral;
    }
}
