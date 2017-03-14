package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.types.QLMoneyType;
import org.lemonade.visitors.interfaces.ExpressionVisitor;

/**
 *
 */
public class MoneyLiteral extends NumericLiteral<Double> {

    public MoneyLiteral(String value) {
        super(new QLMoneyType(), Double.parseDouble(value));
    }

    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
    @Override
    public String toString() {
        return Double.toString(this.getValue());
    }
}
