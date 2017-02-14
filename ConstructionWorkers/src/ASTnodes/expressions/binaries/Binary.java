/**
 * Binary.java.
 */

package ASTnodes.expressions.binaries;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.Expression;

public abstract class Binary extends Expression {

    // Final?
    private final Expression left;
    private final Expression right;

    public Binary(Expression left, Expression right, CodeLocation location) {
        super(location);
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }
}
