package ASTnodes.expressions.binaries;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.Expression;
import ASTnodes.types.Type;

/**
 * Created by LGGX on 10-Feb-17.
 */
public abstract class Binary extends Expression {

    private Expression left;
    private Expression right;

    public Binary(CodeLocation location) {
        super(location);
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    public abstract Type inferType(Type left, Type right);
}
