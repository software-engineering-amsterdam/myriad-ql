package org.ql.ast.expression.literal;

import org.ql.ast.Expression;

import java.lang.*;
import java.lang.String;
import java.math.BigDecimal;

public class DecimalLiteral extends AbstractLiteral<BigDecimal> implements Expression {
    public DecimalLiteral(BigDecimal value) {
        super(value);
    }

    @Override
    public String toString() {
        return value.toPlainString();
    }
}
