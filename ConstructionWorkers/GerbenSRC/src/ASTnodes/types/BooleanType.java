package ASTnodes.types;

import ASTnodes.visitors.AllVisitors;
import ASTnodes.CodeLocation;

/**
 * Created by LGGX on 10-Feb-17.
 */
public class BooleanType extends Type {

    public BooleanType() {
        super();
    }

    public BooleanType(CodeLocation location) {
        super(location);
    }
    /*
    @Override
    public BooleanValue getDefaultValue() {
        return new BooleanValue(false);
    }
    */
    @Override
    public String getTypeName() {
        return "Boolean";
    }

    @Override
    public <T, U> T accept(AllVisitors<T, U> visitor, U context) {
        return visitor.visit(this, context);
    }

}
