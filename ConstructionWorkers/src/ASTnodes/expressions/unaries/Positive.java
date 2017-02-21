/**
 * Positive.java.
 */

package ASTnodes.expressions.unaries;

import ASTnodes.visitors.ExpressionVisitor;
import ASTnodes.CodeLocation;
import ASTnodes.expressions.Expression;

public class Positive extends Unary {

    public Positive(Expression expression, CodeLocation location) {
        super(expression, location);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
