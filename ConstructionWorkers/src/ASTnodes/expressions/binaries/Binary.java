/**
 * Binary.java.
 */

package ASTnodes.expressions.binaries;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.Expression;
import ASTnodes.types.Type;

public abstract class Binary extends Expression {

    // Final?
    private Expression left;
    private Expression right;

    public Binary(Expression left, Expression right, CodeLocation location) {
        super(location);
        this.left = left;
        this.right = right;
    }

    public Type getType(Type left, Type right) {
        return right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }
}
