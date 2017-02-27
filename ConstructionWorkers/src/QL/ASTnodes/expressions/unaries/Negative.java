/**
 * Negative.java.
 */

package QL.ASTnodes.expressions.unaries;

import QL.ASTnodes.visitors.ExpressionVisitor;
import QL.ASTnodes.LineNumber;
import QL.ASTnodes.expressions.Expression;

public class Negative extends Unary {

    public Negative(Expression expression, LineNumber location) {
        super(expression, location);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
