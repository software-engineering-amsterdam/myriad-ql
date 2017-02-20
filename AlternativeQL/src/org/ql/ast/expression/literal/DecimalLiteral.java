package org.ql.ast.expression.literal;

import org.ql.ast.Expression;
import org.ql.ast.expression.Visitor;

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

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
