/**
 * Unary.java.
 */

package ASTnodes.expressions.unaries;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.Expression;
import ASTnodes.types.Type;

public abstract class Unary extends Expression {

    // Final?
    private Expression expression;

    public Unary(Expression expression, CodeLocation location) {
        super(location);
        this.expression = expression;
    }

    public abstract Type getType(Type type);

    public Expression getExpression() {
        return expression;
    }
}
