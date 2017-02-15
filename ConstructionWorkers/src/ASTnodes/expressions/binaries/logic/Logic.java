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

    @Override
    public Type getType(Type left, Type right) {

        BooleanType booleanTest = new BooleanType();

        if (left.equals(right) && right.equals(booleanTest))
            return right;
        else
            return new UndefinedType();
    }
}
