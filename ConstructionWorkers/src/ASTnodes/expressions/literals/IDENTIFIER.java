package ASTnodes.expressions.literals;


import ASTnodes.types.Type;
import ASTnodes.CodeLocation;
import ASTnodes.visitors.AllVisitors;
/**
 * Created by LGGX on 11-Feb-17.
 */
public class IDENTIFIER extends Literal {

    private final String name;

    public IDENTIFIER(String name, CodeLocation location) {
        super(location);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }


    @Override
    public <T> T accept(AllVisitors<T> visitor) {
        return visitor.visit(this);
    }
}
