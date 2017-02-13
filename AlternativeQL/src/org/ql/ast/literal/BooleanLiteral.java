package org.ql.ast.literal;

import org.ql.ast.type.Type;

public class BooleanLiteral extends Literal {
    private boolean booleanLiteral;

    public BooleanLiteral(boolean booleanLiteral) {
        this.booleanLiteral = booleanLiteral;
        setType(new Type("boolean"));
    }

    public boolean getBooleanLiteral() {
        return booleanLiteral;
    }

    public void setBooleanLiteral(boolean booleanLiteral) {
        this.booleanLiteral = booleanLiteral;
    }
}
