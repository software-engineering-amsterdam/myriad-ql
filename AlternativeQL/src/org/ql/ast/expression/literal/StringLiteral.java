package org.ql.ast.expression.literal;

import org.ql.ast.Expression;
import org.ql.ast.expression.ExpressionVisitor;

public class StringLiteral extends Expression {
    private String value;

    public StringLiteral(String value) {
        this.value = value;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) throws Throwable {
        return visitor.visit(this);
    }

    public String getValue() {
        return value;
    }
}
