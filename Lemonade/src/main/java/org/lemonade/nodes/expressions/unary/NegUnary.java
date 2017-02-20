package org.lemonade.nodes.expressions.unary;


import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.expressions.UnaryExpression;
import org.lemonade.visitors.ExpressionVisitor;

/**
 *
 */
public class NegUnary extends UnaryExpression {

    public NegUnary() {
        super();
    }

    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
