/**
 * Equality.java
 */

package ql.astnodes.expressions.binaries.equality;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.Expression;
import ql.astnodes.expressions.binaries.Binary;
import ql.astnodes.types.*;

public abstract class Equality extends Binary {

    public Equality(Expression left, Expression right, LineNumber location) {
        super(left, right, location);
    }

    @Override
    public Type checkType(Type typeToCheckLeft, Type typeToCheckRight) {
        if (typeToCheckLeft == null || typeToCheckRight == null) {
            return new UndefinedType();
        } else {
            Class typeToCheckLeftClass = typeToCheckLeft.getClass();
            if (typeToCheckLeftClass.equals(typeToCheckRight.getClass()) && (typeToCheckLeftClass.equals(MoneyType.class) ||
                    typeToCheckLeftClass.equals(IntegerType.class) || typeToCheckLeftClass.equals(StringType.class))) {
                return new BooleanType();
            } else {
                return new UndefinedType();
            }
        }
    }
}
