/**
 * Parentheses.java.
 */

package ASTnodes.expressions.unaries;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.Expression;
import ASTnodes.visitors.AllVisitors;

public class Parentheses extends Unary {

    public Parentheses(Expression expression, CodeLocation location) {
        super(expression, location);
    }

    @Override
    public <T> T accept(AllVisitors<T> visitor) {
        return visitor.visit(this);
    }
}
