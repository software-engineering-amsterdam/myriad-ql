package org.lemonade.nodes.expressions.unary;

import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.expressions.UnaryExpression;
import org.lemonade.visitors.interfaces.ExpressionVisitor;

public class BangUnary extends UnaryExpression {

    public BangUnary(Expression expr) {
        super(expr);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
