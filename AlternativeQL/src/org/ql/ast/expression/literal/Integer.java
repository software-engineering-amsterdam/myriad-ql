package org.ql.ast.expression.literal;

import org.ql.ast.Expression;

public class Integer implements Expression {
    private int integerLiteral;

    public Integer(int integerLiteral) {
        this.integerLiteral = integerLiteral;
    }

    public int getIntegerLiteral() {
        return integerLiteral;
    }
}
