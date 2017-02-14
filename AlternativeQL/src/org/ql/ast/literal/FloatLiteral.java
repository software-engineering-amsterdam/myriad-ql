package org.ql.ast.literal;

import org.ql.ast.Node;

public class FloatLiteral implements Node {
    private float floatLiteral;

    public FloatLiteral(float floatLiteral) {
        this.floatLiteral = floatLiteral;
    }

    public float getFloatLiteral() {
        return floatLiteral;
    }
}
