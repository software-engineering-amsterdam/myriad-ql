package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.expressions.Literal;
import org.lemonade.nodes.types.QLBooleanType;
import org.lemonade.visitors.interfaces.ExpressionVisitor;

/**
 *
 */
public class BooleanLiteral extends Literal<Boolean> {

    public BooleanLiteral(String value) {
        super(new QLBooleanType(), Boolean.parseBoolean(value));
    }

    public BooleanLiteral(boolean value) {
        super(new QLBooleanType(), value);
    }

    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return Boolean.toString(this.getValue());
    }

}
