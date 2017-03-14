package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.types.QLIntegerType;
import org.lemonade.visitors.interfaces.ExpressionVisitor;

/**
 *
 */
public class IntegerLiteral extends NumericLiteral<Integer>{

    public IntegerLiteral(String value) {
        super(new QLIntegerType(), Integer.parseInt(value));
    }

    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
    @Override
    public String toString() {
        return Integer.toString(this.getValue());
    }

}
