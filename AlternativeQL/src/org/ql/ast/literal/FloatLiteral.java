package org.ql.ast.literal;

import org.ql.ast.type.Type;

public class FloatLiteral extends Literal {
    private float floatLiteral;

    public FloatLiteral(float floatLiteral) {
        this.floatLiteral = floatLiteral;
        setType(new Type("float"));
    }

    public float getFloatLiteral() {
        return floatLiteral;
    }

    public void setFloatLiteral(float floatLiteral) {
        this.floatLiteral = floatLiteral;
    }
}
