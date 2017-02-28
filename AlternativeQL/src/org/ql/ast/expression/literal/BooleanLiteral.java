package org.ql.ast.expression.literal;

import org.ql.ast.Expression;
import org.ql.ast.expression.ExpressionVisitor;

public class BooleanLiteral extends Expression {
    private boolean value;

    public BooleanLiteral(Boolean value) {
        this.value = value;
    }

    @Override
    public <T, C> T accept(ExpressionVisitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }

    public boolean getValue() {
        return value;
    }
}
