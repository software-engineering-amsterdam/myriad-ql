/**
 * Negation.java.
 */

package ql.astnodes.expressions.unaries;

import ql.astnodes.LineNumber;
import ql.astnodes.visitors.ExpressionVisitor;
import ql.astnodes.expressions.Expression;

public class Negation extends Unary {

    public Negation(Expression expression, LineNumber lineNumber) {
        super(expression, lineNumber);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
