package org.ql.ast.expression.literal;

import org.ql.ast.Metadata;
import org.ql.ast.Visitor;

import java.lang.*;
import java.lang.String;
import java.math.BigDecimal;

public class DecimalLiteral extends AbstractLiteral<BigDecimal> {
    public DecimalLiteral(BigDecimal value, Metadata metadata) {
        super(value);
        this.metadata = metadata;
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
