/**
 * Unary.java.
 */

package ASTnodes.expressions.unaries;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.Expression;

public abstract class Unary extends Expression {

    // Final?
    private final Expression expression;

    public Unary(Expression expression, CodeLocation location) {
        super(location);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
}
