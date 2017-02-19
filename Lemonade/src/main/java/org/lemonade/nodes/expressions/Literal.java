package org.lemonade.nodes.expressions;

import org.lemonade.visitors.ExpressionVisitor;

/**
 *
 */
public abstract class Literal extends Expression {

    public Literal() {
        super();
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
