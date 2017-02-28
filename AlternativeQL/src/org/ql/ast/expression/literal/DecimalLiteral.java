package org.ql.ast.expression.literal;

import org.ql.ast.expression.ExpressionVisitor;

import java.math.BigDecimal;

public class DecimalLiteral extends AbstractLiteral<BigDecimal> {
    public DecimalLiteral(BigDecimal value) {
        super(value);
    }

    @Override
    public String toString() {
        return value.toPlainString();
    }

    // TODO specific AST visitor exception OR skip (rely on RuntimeException)
    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) throws Throwable {
        return visitor.visit(this);
    }
}
