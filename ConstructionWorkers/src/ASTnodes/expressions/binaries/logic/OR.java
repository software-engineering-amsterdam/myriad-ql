/**
 * OR.java.
 */

package ASTnodes.expressions.binaries.logic;

import ASTnodes.LineNumber;
import ASTnodes.expressions.Expression;
import ASTnodes.visitors.ExpressionVisitor;

public class OR extends Logic {

    public OR(Expression left, Expression right, LineNumber location) {
        super(left ,right, location);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
