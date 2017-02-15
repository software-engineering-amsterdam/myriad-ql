/**
 * Numerical.java.
 */

package ASTnodes.expressions.binaries.numerical;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.Expression;
import ASTnodes.expressions.binaries.Binary;
import ASTnodes.types.IntegerType;
import ASTnodes.types.MoneyType;
import ASTnodes.types.Type;
import ASTnodes.types.UndefinedType;

public abstract class Numerical extends Binary {

    public Numerical(Expression left, Expression right, CodeLocation location) {
        super(left, right, location);
    }

    @Override
    public Type getType(Type left, Type right) {

        IntegerType intTest = new IntegerType();
        MoneyType moneyTest = new MoneyType();

        if (left.equals(right) && (right.equals(intTest) || right.equals(moneyTest)))
            return right;
        else
            return new UndefinedType();
    }
}
