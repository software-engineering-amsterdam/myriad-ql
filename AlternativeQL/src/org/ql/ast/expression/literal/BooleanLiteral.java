package org.ql.ast.expression.literal;

import org.ql.ast.Expression;

public class BooleanLiteral extends AbstractLiteral<Boolean> implements Expression {
    public BooleanLiteral(Boolean value) {
        super(value);
    }
}
