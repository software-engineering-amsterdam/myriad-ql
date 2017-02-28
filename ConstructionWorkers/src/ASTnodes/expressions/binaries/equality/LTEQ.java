/**
 * LTEQ.java.
 */

package ASTnodes.expressions.binaries.equality;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.Expression;
import ASTnodes.visitors.ExpressionVisitor;

public class LTEQ extends Equality {

    public LTEQ(Expression left, Expression right, CodeLocation location) {
        super(left, right, location);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
