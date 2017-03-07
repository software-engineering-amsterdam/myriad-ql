/**
 * Binary.java.
 */

package ASTnodes.expressions.binaries;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.Expression;
import ASTnodes.types.IntegerType;
import ASTnodes.types.MoneyType;
import ASTnodes.types.Type;
import ASTnodes.types.UndefinedType;

public abstract class Binary extends Expression {

    private final Expression left;
    private final Expression right;

    public Binary(Expression left, Expression right, CodeLocation location) {
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
