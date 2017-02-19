package org.lemonade.nodes.expressions.literal;

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

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
