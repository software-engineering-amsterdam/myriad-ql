package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.expressions.Literal;
import org.lemonade.visitors.ExpressionVisitor;

/**
 *
 */
public class StringLit extends Literal {
    String value;

    public StringLit(String value) {
        this.value = value;
    }

    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return value;
    }
}
