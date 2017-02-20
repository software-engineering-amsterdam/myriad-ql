package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.expressions.Expression;
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

    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
