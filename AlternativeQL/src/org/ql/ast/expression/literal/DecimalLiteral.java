package org.ql.ast.expression.literal;

import org.ql.ast.Expression;
import org.ql.ast.expression.ExpressionVisitor;

import java.math.BigDecimal;

public class DecimalLiteral extends Expression {
    private final BigDecimal value;

    public DecimalLiteral(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toPlainString();
    }

    @Override
    public <T, C> T accept(ExpressionVisitor<T, C> visitor, C context) {
        return visitor.visitDecimal(this, context);
    }

    public BigDecimal getValue() {
        return value;
    }
}
