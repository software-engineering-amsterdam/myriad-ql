/**
 * Subtraction.java.
 */

package ql.astnodes.expressions.binaries.numerical;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.Expression;
import ql.astnodes.visitors.ExpressionVisitor;

public class Subtraction extends Numerical {

    public Subtraction(Expression left, Expression right, LineNumber location) {
        super(left, right, location);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
