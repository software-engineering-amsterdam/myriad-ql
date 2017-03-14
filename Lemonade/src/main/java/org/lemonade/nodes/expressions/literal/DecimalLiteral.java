package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.types.QLDecimalType;
import org.lemonade.visitors.interfaces.ExpressionVisitor;

/**
 *
 */
public class DecimalLiteral extends NumericLiteral<Double>{

    public DecimalLiteral(String value) {
        super(new QLDecimalType(), Double.parseDouble(value));
    }

    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return Double.toString(this.getValue());
    }

}
