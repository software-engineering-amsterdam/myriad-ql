/**
 * OR.java.
 */

package ASTnodes.expressions.binaries.logic;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.Expression;
import ASTnodes.visitors.ExpressionVisitor;

public class OR extends Logic {

    public OR(Expression left, Expression right, CodeLocation location) {
        super(left ,right, location);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
