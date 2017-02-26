/**
 * Equality.java
 */

package ASTnodes.expressions.binaries.equality;

import ASTnodes.LineNumber;
import ASTnodes.expressions.Expression;
import ASTnodes.expressions.binaries.Binary;
import ASTnodes.types.*;

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
