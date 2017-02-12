package ASTnodes.expressions.binaries.logic;

import ASTnodes.expressions.binaries.Binary;
import ASTnodes.CodeLocation;
import ASTnodes.types.BooleanType;
import ASTnodes.types.Type;
import ASTnodes.types.UndefinedType;

/**
 * Created by LGGX on 10-Feb-17.
 */
public abstract class Logic extends Binary {

    public Logic(CodeLocation location) {
        super(location);
    }

    @Override
    public Type inferType(Type left, Type right) {

        if (left.equals(right)) {
            if (left.equals(new BooleanType())) {
                return new BooleanType();
            }
        }

        return new UndefinedType();
    }
}
