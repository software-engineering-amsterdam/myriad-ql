/**
 * AND.java.
 */

package ASTnodes.expressions.binaries.logic;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.Expression;
import ASTnodes.visitors.ExpressionVisitor;

public class AND extends Logic{

    public AND(Expression left, Expression right, CodeLocation location) {
        super(left, right, location);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
