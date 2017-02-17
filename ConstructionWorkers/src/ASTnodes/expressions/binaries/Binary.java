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

    // Final?
    private Expression left;
    private Expression right;

    public Binary(Expression left, Expression right, CodeLocation location) {
        super(location);
        this.left = left;
        this.right = right;
    }

    public Type getType(Type left, Type right) {

        String intTest = new IntegerType().getClass().getName();
        String moneyTest = new MoneyType().getClass().getName();
        String leftString = left.getClass().getName();
        String rightString = right.getClass().getName();

        if (leftString == rightString && (rightString == moneyTest || rightString == intTest))
            return right;
        else
            return new UndefinedType();
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }
}
