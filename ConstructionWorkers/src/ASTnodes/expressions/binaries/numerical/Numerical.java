/**
 * Numerical.java.
 */

package ASTnodes.expressions.binaries.numerical;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.Expression;
import ASTnodes.expressions.binaries.Binary;
import ASTnodes.expressions.literals.MyInteger;
import ASTnodes.types.IntegerType;
import ASTnodes.types.MoneyType;
import ASTnodes.types.Type;
import ASTnodes.types.UndefinedType;

public abstract class Numerical extends Binary {

    public Numerical(Expression left, Expression right, CodeLocation location) {
        super(left, right, location);
    }

    @Override
    public Type checkType(Type typeToCheckLeft, Type typeToCheckRight) {
        if (typeToCheckLeft == null || typeToCheckRight == null) {
            return new UndefinedType();
        } else {
            Class typeToCheckLeftClass = typeToCheckLeft.getClass();
            if (typeToCheckLeftClass.equals(typeToCheckRight.getClass()) && (typeToCheckLeftClass == MoneyType.class ||
                    typeToCheckLeftClass == IntegerType.class)) {
                return typeToCheckRight;
            } else {
                return new UndefinedType();
            }
        }
    }
}
