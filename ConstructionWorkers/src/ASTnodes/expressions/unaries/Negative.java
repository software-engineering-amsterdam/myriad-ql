/**
 * Negative.java.
 */

package ASTnodes.expressions.unaries;

import ASTnodes.visitors.ExpressionVisitor;
import ASTnodes.CodeLocation;
import ASTnodes.expressions.Expression;

public class Negative extends Unary {

    public Negative(Expression expression, CodeLocation location) {
        super(expression, location);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
