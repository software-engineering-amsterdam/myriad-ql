package org.ql.ast.expression.literal;

import org.ql.ast.expression.ExpressionVisitor;

public class StringLiteral extends AbstractLiteral<String> {
    public StringLiteral(String value) {
        super(value);
    }

    @Override
    public <T, C> T accept(ExpressionVisitor<T, C> visitor, C context) {
         return visitor.visit(this, context);
     }
}
