package org.ql.ast.literal;

import org.ql.ast.type.Type;

public class StringLiteral extends Literal {
    private String stringLiteral;

    public StringLiteral(String stringLiteral) {
        this.stringLiteral = stringLiteral;
        setType(new Type("string"));
    }

    public String getStringLiteral() {
        return stringLiteral;
    }

    public void setStringLiteral(String stringLiteral) {
        this.stringLiteral = stringLiteral;
    }
}
