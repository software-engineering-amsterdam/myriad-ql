package org.lemonade.nodes.expressions.binary;

import org.lemonade.nodes.expressions.BinaryExpression;
import org.lemonade.nodes.expressions.Expression;
import org.lemonade.visitors.interfaces.ExpressionVisitor;

/**
 *
 */
public class LTBinary extends BinaryExpression {

    public LTBinary(Expression left, Expression right) {
        super(left, right);
    }

    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
