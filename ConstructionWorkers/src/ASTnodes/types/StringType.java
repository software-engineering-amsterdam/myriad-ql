package ASTnodes.types;

import ASTnodes.visitors.AllVisitors;
import ASTnodes.CodeLocation;

/**
 * Created by LGGX on 10-Feb-17.
 */
public class StringType extends Type {


    public StringType(CodeLocation location) {
        super(location);
    }


    @Override
    public String getTypeName() {
        return "String";
    }

    @Override
    public <T> T accept(AllVisitors<T> visitor) {
        return visitor.visit(this);
    }
}
