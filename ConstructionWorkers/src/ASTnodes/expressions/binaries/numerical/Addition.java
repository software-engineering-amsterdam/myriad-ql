/**
 * Addition.java.
 */

package ASTnodes.expressions.binaries.numerical;

import ASTnodes.LineNumber;
import ASTnodes.expressions.Expression;
import ASTnodes.visitors.ExpressionVisitor;

public class Addition extends Numerical {

    public Addition(Expression left, Expression right, LineNumber location) {
        super(left, right, location);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
