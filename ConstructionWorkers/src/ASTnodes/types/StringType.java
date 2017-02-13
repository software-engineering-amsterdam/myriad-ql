package ASTnodes.types;

import ASTnodes.visitors.AllVisitors;
import ASTnodes.CodeLocation;

/**
 * Created by LGGX on 10-Feb-17.
 */
public class StringType extends Type {

    public StringType() {
        super();
    }

    public StringType(CodeLocation location) {
        super(location);
    }

    /*
    @Override
    public StringValue getDefaultValue() {
        return new StringValue("");
    }
    */

    @Override
    public String getTypeName() {
        return "String";
    }

    @Override
    public <T, U> T accept(AllVisitors<T, U> visitor, U context) {
        return visitor.visit(this, context);
    }
}
