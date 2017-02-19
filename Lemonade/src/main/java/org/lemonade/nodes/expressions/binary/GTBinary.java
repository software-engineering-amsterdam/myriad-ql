package org.lemonade.nodes.expressions.binary;

import org.lemonade.nodes.expressions.BinaryExpression;
import org.lemonade.visitors.ExpressionVisitor;

/**
 *
 */
public class GTBinary extends BinaryExpression {

    public GTBinary() {
        super();
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
