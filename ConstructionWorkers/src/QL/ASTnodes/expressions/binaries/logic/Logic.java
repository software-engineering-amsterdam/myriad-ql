/**
 * Logic.java.
 */

package QL.ASTnodes.expressions.binaries.logic;

import QL.ASTnodes.expressions.Expression;
import QL.ASTnodes.expressions.binaries.Binary;
import QL.ASTnodes.LineNumber;
import QL.ASTnodes.types.BooleanType;
import QL.ASTnodes.types.Type;
import QL.ASTnodes.types.UndefinedType;

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
