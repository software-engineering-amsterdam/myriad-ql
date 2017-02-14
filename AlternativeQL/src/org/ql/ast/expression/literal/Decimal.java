package org.ql.ast.expression.literal;

import org.ql.ast.Expression;

import java.math.BigDecimal;

public class Decimal implements Expression {
    private BigDecimal decimalLiteral;

    public Decimal(BigDecimal decimalLiteral) {
        this.decimalLiteral = decimalLiteral;
    }

    public BigDecimal getDecimalLiteral() {
        return decimalLiteral;
    }
}
