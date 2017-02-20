package org.lemonade.nodes.expressions.unary;


import org.lemonade.nodes.expressions.UnaryExpression;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class NegUnary extends UnaryExpression {

    public NegUnary() {
        super();
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
