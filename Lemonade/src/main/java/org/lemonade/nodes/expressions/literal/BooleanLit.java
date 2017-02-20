package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.expressions.Literal;
import org.lemonade.visitors.ExpressionVisitor;

/**
 *
 */
public class BooleanLit extends Literal {
    private boolean value;

    public BooleanLit(String value) {
        this.value = Boolean.parseBoolean(value);
    }

    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }
}
