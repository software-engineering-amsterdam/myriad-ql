/**
 * Logic.java.
 */

package ASTnodes.expressions.binaries.logic;

import ASTnodes.expressions.Expression;
import ASTnodes.expressions.binaries.Binary;
import ASTnodes.LineNumber;
import ASTnodes.types.BooleanType;
import ASTnodes.types.Type;
import ASTnodes.types.UndefinedType;

public abstract class Logic extends Binary {

    public Logic(Expression left, Expression right, LineNumber location) {
        super(left, right, location);
    }

    @Override
    public Type checkType(Type typeToCheckLeft, Type typeToCheckRight) {
        if (typeToCheckLeft == null || typeToCheckRight == null) {
            return new UndefinedType();
        } else {
            if (typeToCheckLeft.getClass().equals(typeToCheckRight.getClass()) &&
                    typeToCheckLeft.getClass().equals(BooleanType.class)) {
                return typeToCheckLeft;
            } else {
                return new UndefinedType();
            }
        }
    }
}
