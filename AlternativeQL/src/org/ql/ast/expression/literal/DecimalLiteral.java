package org.ql.ast.expression.literal;

import org.ql.ast.Visitor;

import java.lang.*;
import java.lang.String;
import java.math.BigDecimal;

public class DecimalLiteral extends AbstractLiteral<BigDecimal> {
    public DecimalLiteral(BigDecimal value) {
        super(value);
    }

    @Override
    public String toString() {
        return value.toPlainString();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
