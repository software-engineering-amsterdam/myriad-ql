package org.ql.ast.expression;

import org.ql.ast.identifier.Identifier;

public class Parameter extends Expression {
    private final Identifier id;

    public Parameter(Identifier id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public <T, C> T accept(ExpressionVisitor<T, C> visitor, C context) {
        return visitor.visitParameter(this, context);
    }

    public Identifier getId() {
        return id;
    }
}
