package ASTnodes.types;

import ASTnodes.visitors.AllVisitors;
import ASTnodes.CodeLocation;

/**
 * Created by LGGX on 10-Feb-17.
 */

public class UndefinedType extends Type {


    public UndefinedType(CodeLocation location) {
        super(location);
    }


    @Override
    public String getTypeName() {
        return "Unknown";
    }

    @Override
    public <T> T accept(AllVisitors<T> visitor) {
        new AssertionError("Undefined type with no accept was accessed.");
        return null;
    }
}
