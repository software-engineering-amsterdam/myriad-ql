package org.ql.ast.expression;

import org.ql.ast.Expression;

public class BooleanLiteral implements Expression {
    private boolean booleanLiteral;

    public BooleanLiteral(boolean booleanLiteral) {
        this.booleanLiteral = booleanLiteral;
    }

    public boolean getBooleanLiteral() {
        return booleanLiteral;
    }
}
