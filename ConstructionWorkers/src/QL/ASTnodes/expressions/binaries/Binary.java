/**
 * Binary.java.
 */

package QL.ASTnodes.expressions.binaries;

import QL.ASTnodes.LineNumber;
import QL.ASTnodes.expressions.Expression;
import QL.ASTnodes.types.Type;

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
