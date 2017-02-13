package ASTnodes.expressions.binaries.equality;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.binaries.Binary;
import ASTnodes.types.BooleanType;
import ASTnodes.types.Type;
import ASTnodes.types.UndefinedType;

/**
 * Created by LGGX on 10-Feb-17.
 */
public abstract class Equality extends Binary {

    public Equality(CodeLocation location) {
        super(location);
    }

}
