package org.lemonade.nodes.expressions.unary;

import org.lemonade.nodes.expressions.UnaryExpression;
import org.lemonade.visitors.ExpressionVisitor;

/**
 *
 */
public class BangUnary extends UnaryExpression {

    public BangUnary() {
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
