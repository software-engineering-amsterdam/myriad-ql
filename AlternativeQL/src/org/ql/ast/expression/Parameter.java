package org.ql.ast.expression;

import org.ql.ast.Expression;
import org.ql.ast.Identifier;

public class Parameter implements Expression {
    private final Identifier id;

    public Parameter(Identifier id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
