package org.ql.ast.expression.literal;

import org.ql.ast.expression.Expression;
import org.ql.ast.expression.ExpressionVisitor;

public class StringLiteral extends Expression {
    private final String value;

    public StringLiteral(String value) {
        this.value = value;
    }

    @Override
    public <T, C> T accept(ExpressionVisitor<T, C> visitor, C context) {
        return visitor.visitString(this, context);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
