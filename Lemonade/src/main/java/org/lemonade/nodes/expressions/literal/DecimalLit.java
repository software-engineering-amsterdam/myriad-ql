package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.expressions.Literal;
import org.lemonade.visitors.ExpressionVisitor;

/**
 *
 */
public class DecimalLit extends Literal {
    private double value;

    public DecimalLit(String value) {
        this.value = Double.parseDouble(value);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
