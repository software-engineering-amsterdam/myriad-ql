package ASTnodes.types;

import ASTnodes.visitors.AllVisitors;
import ASTnodes.CodeLocation;

/**
 * Created by LGGX on 10-Feb-17.
 */

public class UndefinedType extends Type {

    public UndefinedType() {
        super();
    }

    public UndefinedType(CodeLocation location) {
        super(location);
    }

    /*
    @Override
    public UnknownValue getDefaultValue() {
        return new UnknownValue();
    }
    */

    @Override
    public String getTypeName() {
        return "Unknown";
    }

    @Override
    public <T, U> T accept(AllVisitors<T, U> visitor, U context) {
        new AssertionError("Undefined type with no accept was accessed.");
        return null;
    }
}
