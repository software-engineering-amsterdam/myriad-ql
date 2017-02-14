package org.ql.ast.expression.literal;

import org.ql.ast.Expression;

import java.lang.*;
import java.lang.String;

public class Boolean implements Expression {
    private boolean booleanLiteral;

    public Boolean(boolean booleanLiteral) {
        this.booleanLiteral = booleanLiteral;
    }

    public boolean getBooleanLiteral() {
        return booleanLiteral;
    }

    @Override
    public String toString() {
        return String.valueOf(booleanLiteral);
    }
}
