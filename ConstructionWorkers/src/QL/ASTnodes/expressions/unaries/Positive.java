/**
 * Positive.java.
 */

package QL.ASTnodes.expressions.unaries;

import QL.ASTnodes.visitors.ExpressionVisitor;
import QL.ASTnodes.LineNumber;
import QL.ASTnodes.expressions.Expression;

public class Positive extends Unary {

    public Positive(Expression expression, LineNumber location) {
        super(expression, location);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
