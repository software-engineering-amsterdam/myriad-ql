package org.ql.ast.expression.literal;

import org.ql.ast.expression.ExpressionVisitor;

public class BooleanLiteral extends AbstractLiteral<Boolean> {
    public BooleanLiteral(Boolean value) {
        super(value);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) throws Throwable {
        return visitor.visit(this);
    }
}
