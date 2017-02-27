/**
 * Numerical.java.
 */

package QL.ASTnodes.expressions.binaries.numerical;

import QL.ASTnodes.LineNumber;
import QL.ASTnodes.expressions.Expression;
import QL.ASTnodes.expressions.binaries.Binary;
import QL.ASTnodes.types.IntegerType;
import QL.ASTnodes.types.MoneyType;
import QL.ASTnodes.types.Type;
import QL.ASTnodes.types.UndefinedType;

public abstract class Numerical extends Binary {

    public Numerical(Expression left, Expression right, LineNumber location) {
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
