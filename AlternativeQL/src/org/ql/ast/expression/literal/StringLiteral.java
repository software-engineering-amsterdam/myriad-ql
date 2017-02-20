package org.ql.ast.expression.literal;

import org.ql.ast.Expression;
import org.ql.ast.expression.Visitor;

public class StringLiteral extends AbstractLiteral<String> implements Expression {
    public StringLiteral(String value) {
        super(value);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
