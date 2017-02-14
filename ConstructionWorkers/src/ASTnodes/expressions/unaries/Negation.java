/**
 * Negation.java.
 */

package ASTnodes.expressions.unaries;

import ASTnodes.visitors.AllVisitors;
import ASTnodes.CodeLocation;
import ASTnodes.expressions.Expression;

public class Negation extends Unary {

    public Negation(Expression expression, CodeLocation location) {
        super(expression, location);
    }

    @Override
    public <T> T accept(AllVisitors<T> visitor) {
        return visitor.visit(this);
    }
}
