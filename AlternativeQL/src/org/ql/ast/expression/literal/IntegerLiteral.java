package org.ql.ast.expression.literal;

import org.ql.ast.expression.Expression;
import org.ql.ast.expression.ExpressionVisitor;

public class IntegerLiteral extends Expression {
    private final int value;

    public IntegerLiteral(Integer value) {
        this.value = value;
    }

    @Override
    public <T, C> T accept(ExpressionVisitor<T, C> visitor, C context) {
        return visitor.visitInteger(this, context);
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
