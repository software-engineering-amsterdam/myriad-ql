package org.ql.ast.expression.literal;

import org.ql.ast.expression.ExpressionVisitor;

public class IntegerLiteral extends AbstractLiteral<Integer> {
    public IntegerLiteral(Integer value) {
        super(value);
    }

    @Override
    public <T, C> T accept(ExpressionVisitor<T, C> visitor, C context) {
         return visitor.visit(this, context);
     }
}
