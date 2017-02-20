package org.lemonade.nodes.expressions.unary;

import org.lemonade.nodes.expressions.UnaryExpression;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class BangUnary extends UnaryExpression {

    public BangUnary() {
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
