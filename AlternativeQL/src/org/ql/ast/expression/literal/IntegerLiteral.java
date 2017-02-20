package org.ql.ast.expression.literal;

import org.ql.ast.Visitor;

public class IntegerLiteral extends AbstractLiteral<Integer> {
    public IntegerLiteral(Integer value) {
        super(value);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
