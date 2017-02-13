package org.ql.ast.literal;

import org.ql.ast.type.Type;

public class Literal {
    private Type type;

    public Literal() {
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
