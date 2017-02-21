package org.ql.ast.expression.literal;

import org.ql.ast.Expression;
import org.ql.ast.expression.Visitor;

public class StringLiteral extends AbstractLiteral<String> {
    public StringLiteral(String value) {
        super(value);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) throws Throwable {
        return visitor.visit(this);
    }
}
