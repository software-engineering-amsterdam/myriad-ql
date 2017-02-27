package org.ql.ast.expression.literal;

import org.ql.ast.expression.ExpressionVisitor;

public class IntegerLiteral extends AbstractLiteral<Integer> {
    public IntegerLiteral(Integer value) {
        super(value);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) throws Throwable {
        return visitor.visit(this);
    }
}
