package org.ql.ast.expression.literal;

import org.ql.ast.expression.Expression;
import org.ql.ast.expression.ExpressionVisitor;

public class BooleanLiteral extends Expression {
    private final boolean value;

    public BooleanLiteral(Boolean value) {
        this.value = value;
    }

    @Override
    public <T, C> T accept(ExpressionVisitor<T, C> visitor, C context) {
        return visitor.visitBoolean(this, context);
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
