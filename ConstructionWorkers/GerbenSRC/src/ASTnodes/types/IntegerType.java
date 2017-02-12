package ASTnodes.types;

import ASTnodes.visitors.AllVisitors;
import ASTnodes.CodeLocation;

/**
 * Created by LGGX on 10-Feb-17.
 */
public class IntegerType extends Type {

    public IntegerType() {
        super();
    }

    public IntegerType(CodeLocation location) {
        super(location);
    }

    /*
    @Override
    public IntegerValue getDefaultValue() {
        return new IntegerValue(0);
    }
    */

    @Override
    public String getTypeName() {
        return "Integer";
    }


    @Override
    public <T, U> T accept(AllVisitors<T, U> visitor, U context) {
        return visitor.visit(this, context);
    }
}
