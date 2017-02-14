package org.ql.ast.expression.literals;

import org.ql.ast.Expression;

public class IntegerLiteral implements Expression {
    private int integerLiteral;

    public IntegerLiteral(int integerLiteral) {
        this.integerLiteral = integerLiteral;
    }

    public int getIntegerLiteral() {
        return integerLiteral;
    }
}
