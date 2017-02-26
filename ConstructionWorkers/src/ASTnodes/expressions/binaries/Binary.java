/**
 * Binary.java.
 */

package ASTnodes.expressions.binaries;

import ASTnodes.LineNumber;
import ASTnodes.expressions.Expression;
import ASTnodes.types.Type;

public abstract class Binary extends Expression {

    private final Expression left;
    private final Expression right;

    public Binary(Expression left, Expression right, LineNumber location) {
        super(location);
        this.left = left;
        this.right = right;
    }

    public abstract Type checkType(Type typeToCheckLeft, Type typeToCheckRight);

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }
}
