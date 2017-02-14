package org.ql.ast.expression.literals;

import org.ql.ast.Expression;

public class FloatLiteral implements Expression {
    private float floatLiteral;

    public FloatLiteral(float floatLiteral) {
        this.floatLiteral = floatLiteral;
    }

    public float getFloatLiteral() {
        return floatLiteral;
    }
}
