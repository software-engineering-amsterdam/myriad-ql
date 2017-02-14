package org.ql.ast.literal;

import org.ql.ast.type.Type;

public class IntegerLiteral extends Literal {
    private int integerLiteral;

    public IntegerLiteral(int integerLiteral) {
        this.integerLiteral = integerLiteral;
        setType(new Type("integer"));
    }

    public int getIntegerLiteral() {
        return integerLiteral;
    }

    public void setIntegerLiteral(int integerLiteral) {
        this.integerLiteral = integerLiteral;
    }
}
