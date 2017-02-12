package ASTnodes.types;

import ASTnodes.Node;
import ASTnodes.CodeLocation;
import ASTnodes.visitors.AllVisitors;

/**
 * Created by LGGX on 09-Feb-17.
 */
public abstract class Type extends Node {

    public Type() {
        super();
    }

    public Type(CodeLocation location) {
        super(location);
    }

    //public abstract Value getDefaultValue();

    public abstract String getTypeName();

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return (this.getClass() == obj.getClass());
    }

    @Override
    public int hashCode() {
        return (this.getClass().hashCode());
    }

    public abstract <T, U> T accept(AllVisitors<T, U> visitor, U context);
}
