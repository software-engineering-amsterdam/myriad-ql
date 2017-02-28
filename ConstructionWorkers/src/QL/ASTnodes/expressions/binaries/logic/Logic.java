/**
 * Logic.java.
 */

package ql.astnodes.expressions.binaries.logic;

import ql.astnodes.expressions.Expression;
import ql.astnodes.expressions.binaries.Binary;
import ql.astnodes.LineNumber;
import ql.astnodes.types.BooleanType;
import ql.astnodes.types.Type;
import ql.astnodes.types.UndefinedType;

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
