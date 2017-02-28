/**
 * Numerical.java.
 */

package ql.astnodes.expressions.binaries.numerical;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.Expression;
import ql.astnodes.expressions.binaries.Binary;
import ql.astnodes.types.IntegerType;
import ql.astnodes.types.MoneyType;
import ql.astnodes.types.Type;
import ql.astnodes.types.UndefinedType;

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
