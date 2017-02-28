package org.ql.ast.expression.literal;

import org.ql.ast.Expression;
import org.ql.ast.expression.ExpressionVisitor;

public class BooleanLiteral extends Expression {
    private boolean value;

    public BooleanLiteral(Boolean value) {
        this.value = value;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) throws Throwable {
        return visitor.visit(this);
    }

    public boolean getValue() {
        return value;
    }
}
