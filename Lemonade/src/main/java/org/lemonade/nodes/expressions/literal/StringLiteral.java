package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.expressions.Literal;
import org.lemonade.nodes.types.QLStringType;
import org.lemonade.visitors.interfaces.ExpressionVisitor;

/**
 *
 */
public class StringLiteral extends Literal<String> {

    public StringLiteral(String value) {
        super(new QLStringType(), value);
    }

    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
