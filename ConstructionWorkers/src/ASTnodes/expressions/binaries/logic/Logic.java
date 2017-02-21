/**
 * Logic.java.
 */

package ASTnodes.expressions.binaries.logic;

import ASTnodes.expressions.Expression;
import ASTnodes.expressions.binaries.Binary;
import ASTnodes.CodeLocation;
import ASTnodes.types.BooleanType;
import ASTnodes.types.Type;
import ASTnodes.types.UndefinedType;

public abstract class Logic extends Binary {

    public Logic(Expression left, Expression right, CodeLocation location) {
        super(left, right, location);
    }

    // TODO: refactor!
    public Type getType(Type type) {
        String booleanTest = new BooleanType().getClass().getName();
        String typeString = type.getClass().getName();

        if (typeString == booleanTest)
            return type;
        else
            return new UndefinedType();
    }

    // TODO: refactor!
    @Override
    public Type getType(Type left, Type right) {
        String booleanTest = new BooleanType().getClass().getName();
        String leftString = left.getClass().getName();
        String rightString = right.getClass().getName();

        if (leftString == rightString && rightString == booleanTest)
            return right;
        else
            return new UndefinedType();
    }
}
