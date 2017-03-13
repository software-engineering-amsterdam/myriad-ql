package org.lemonade.nodes.expressions.binary;

import org.lemonade.nodes.expressions.BinaryExpression;
import org.lemonade.nodes.expressions.Expression;
import org.lemonade.visitors.interfaces.ExpressionVisitor;

/**
 *
 */
public class DivideBinary extends BinaryExpression {

    public DivideBinary(Expression left, Expression right) {
        super(left, right);
    }
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
