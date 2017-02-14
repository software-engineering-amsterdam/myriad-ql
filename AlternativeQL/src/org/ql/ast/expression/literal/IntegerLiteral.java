package org.ql.ast.expression.literal;

import org.ql.ast.Expression;

public class IntegerLiteral extends AbstractLiteral<Integer> implements Expression {
    public IntegerLiteral(Integer value) {
        super(value);
    }
}
