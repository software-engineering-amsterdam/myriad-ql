package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.expressions.Literal;
import org.lemonade.visitors.ExpressionVisitor;

/**
 *
 */
public class IntegerLit extends Literal {
    private int value;

    public IntegerLit(String value) {
        this.value = Integer.parseInt(value);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
