package org.ql.ast.expression.literal;

import org.ql.ast.expression.Visitor;

public class IntegerLiteral extends AbstractLiteral<Integer> {
    public IntegerLiteral(Integer value) {
        super(value);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) throws Throwable {
        return visitor.visit(this);
    }
}
