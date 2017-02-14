package org.ql.ast.expression.literal;

import org.ql.ast.Expression;

import java.lang.*;
import java.lang.String;

public class Integer implements Expression {
    private int integerLiteral;

    public Integer(int integerLiteral) {
        this.integerLiteral = integerLiteral;
    }

    public int getIntegerLiteral() {
        return integerLiteral;
    }

    @Override
    public String toString() {
        return String.valueOf(integerLiteral);
    }
}
