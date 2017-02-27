/**
 * OR.java.
 */

package QL.ASTnodes.expressions.binaries.logic;

import QL.ASTnodes.LineNumber;
import QL.ASTnodes.expressions.Expression;
import QL.ASTnodes.visitors.ExpressionVisitor;

public class OR extends Logic {

    public OR(Expression left, Expression right, LineNumber location) {
        super(left ,right, location);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
