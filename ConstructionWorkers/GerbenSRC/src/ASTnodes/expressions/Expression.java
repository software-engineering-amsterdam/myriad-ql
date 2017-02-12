package ASTnodes.expressions;

import ASTnodes.Node;
import ASTnodes.CodeLocation;
import ASTnodes.visitors.AllVisitors;
import ASTnodes.types.Type;

/**
 * Created by LGGX on 09-Feb-17.
 */
public abstract class Expression extends Node {

    private Type currentType;

    public Expression(CodeLocation location) {
        super(location);
    }

    public Type getType() {
        return this.currentType;
    }

    public abstract <T, U> T accept(AllVisitors<T, U> visitor, U context);

}
