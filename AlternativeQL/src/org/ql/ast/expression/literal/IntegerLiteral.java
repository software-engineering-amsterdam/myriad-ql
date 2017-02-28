package org.ql.ast.expression.literal;

import org.ql.ast.Expression;
import org.ql.ast.expression.ExpressionVisitor;

public class IntegerLiteral extends Expression {
    private int value;

    public IntegerLiteral(Integer value) {
        this.value = value;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) throws Throwable {
        return visitor.visit(this);
    }

    public int getValue() {
        return value;
    }
}
