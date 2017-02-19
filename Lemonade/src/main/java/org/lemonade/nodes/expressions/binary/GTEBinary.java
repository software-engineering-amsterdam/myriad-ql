package org.lemonade.nodes.expressions.binary;

import org.lemonade.nodes.expressions.BinaryExpression;
import org.lemonade.visitors.ExpressionVisitor;

/**
 *
 */
public class GTEBinary extends BinaryExpression {

    public GTEBinary() {
        super();
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
