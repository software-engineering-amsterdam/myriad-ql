package org.ql.ast.expression.literals;

import org.ql.ast.Expression;

public class StringLiteral implements Expression {
    private String stringLiteral;

    public StringLiteral(String stringLiteral) {
        this.stringLiteral = stringLiteral;
    }

    public String toString() {
        return stringLiteral;
    }
}
