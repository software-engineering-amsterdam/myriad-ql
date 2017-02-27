/**
 * Equality.java
 */

package QL.ASTnodes.expressions.binaries.equality;

import QL.ASTnodes.LineNumber;
import QL.ASTnodes.expressions.Expression;
import QL.ASTnodes.expressions.binaries.Binary;
import QL.ASTnodes.types.*;

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
