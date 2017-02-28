/**
 * Negative.java.
 */

package ql.astnodes.expressions.unaries;

import ql.astnodes.visitors.ExpressionVisitor;
import ql.astnodes.LineNumber;
import ql.astnodes.expressions.Expression;

public class Negative extends Unary {

    public Negative(Expression expression, LineNumber location) {
        super(expression, location);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
