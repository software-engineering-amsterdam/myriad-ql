package ASTnodes.types;

import ASTnodes.CodeLocation;
import ASTnodes.visitors.AllVisitors;

/**
 * Created by LGGX on 13-Feb-17.
 */
public class MoneyType extends Type {

    public MoneyType(CodeLocation location) {
        super(location);
    }


    @Override
    public String getTypeName() {
        return "Money";
    }


    @Override
    public <T> T accept(AllVisitors<T> visitor) {
        return visitor.visit(this);
    }
}
