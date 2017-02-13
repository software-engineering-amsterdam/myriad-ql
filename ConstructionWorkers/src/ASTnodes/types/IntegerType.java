package ASTnodes.types;

import ASTnodes.visitors.AllVisitors;
import ASTnodes.CodeLocation;

/**
 * Created by LGGX on 10-Feb-17.
 */
public class IntegerType extends Type {


    public IntegerType(CodeLocation location) {
        super(location);
    }

    @Override
    public String getTypeName() {
        return "Integer";
    }


    @Override
    public <T> T accept(AllVisitors<T> visitor) {
        return visitor.visit(this);
    }
}
