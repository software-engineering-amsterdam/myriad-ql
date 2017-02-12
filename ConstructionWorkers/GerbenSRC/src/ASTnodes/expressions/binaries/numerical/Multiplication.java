package ASTnodes.expressions.binaries.numerical;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.Expression;
import ASTnodes.types.IntegerType;
import ASTnodes.types.Type;
import ASTnodes.types.UndefinedType;
import ASTnodes.visitors.AllVisitors;

/**
 * Created by LGGX on 10-Feb-17.
 */

public class Multiplication extends Numerical {

    private final Expression left;
    private final Expression right;

    public Multiplication(Expression left, Expression right, CodeLocation location) {
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

    @Override
    public <T, U> T accept(AllVisitors<T, U> visitor, U context) {
        return visitor.visit(this, context);
    }

    @Override
    public String toString() {
        return left + "*" + right;
    }

    @Override
    public Type inferType(Type left, Type right) {

        if (left.equals(right)) {
            if (left.equals(new IntegerType())) {
                return left;
            }
        } else {
            if (left.equals(new IntegerType()) && right.equals(new IntegerType())) {
                return right;
            } else if (right.equals(new IntegerType()) && left.equals(new IntegerType())) {
                return left;
            }
        }

        return new UndefinedType();
    }

}
