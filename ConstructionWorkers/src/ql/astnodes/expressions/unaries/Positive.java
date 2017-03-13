/**
 * Positive.java.
 */

package ql.astnodes.expressions.unaries;

import ql.visitorinterfaces.ExpressionVisitor;
import ql.astnodes.LineNumber;
import ql.astnodes.expressions.Expression;

public class Positive extends Unary {

    public Positive(Expression expression, LineNumber lineNumber) {
        super(expression, lineNumber);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
