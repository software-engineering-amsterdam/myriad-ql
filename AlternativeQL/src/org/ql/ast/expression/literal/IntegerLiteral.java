package org.ql.ast.expression.literal;

import org.ql.ast.Expression;
import org.ql.ast.expression.Visitor;

public class IntegerLiteral extends AbstractLiteral<Integer> implements Expression {
    public IntegerLiteral(Integer value) {
        super(value);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
