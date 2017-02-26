package org.ql.ast.expression.literal;

import org.ql.ast.Expression;

public class StringLiteral extends AbstractLiteral<String> implements Expression {
    public StringLiteral(String value) {
        super(value);
    }
}
