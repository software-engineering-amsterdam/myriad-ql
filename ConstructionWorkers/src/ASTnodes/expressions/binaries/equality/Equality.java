/**
 * Equality.java
 */

package ASTnodes.expressions.binaries.equality;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.Expression;
import ASTnodes.expressions.binaries.Binary;
import ASTnodes.types.*;

public abstract class Equality extends Binary {

    public Equality(Expression left, Expression right, CodeLocation location) {
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
