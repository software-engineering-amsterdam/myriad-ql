package org.ql.ast.expression.literal;

import org.ql.ast.Expression;

public class Boolean implements Expression {
    private boolean booleanLiteral;

    public Boolean(boolean booleanLiteral) {
        this.booleanLiteral = booleanLiteral;
    }

    public boolean getBooleanLiteral() {
        return booleanLiteral;
    }
}
