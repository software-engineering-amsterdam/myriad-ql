package ASTnodes.expressions.literals;

import ASTnodes.types.Type;
import ASTnodes.CodeLocation;
import ASTnodes.types.StringType;
import ASTnodes.visitors.AllVisitors;

/**
 * Created by LGGX on 10-Feb-17.
 */
public class STRING extends Literal {

    private final Type type;
    private final String literal;


    public STRING(String literal, CodeLocation location) {
        super(location);
        this.literal = literal;
        this.type = new StringType(location);
    }

    @Override
    public String getLiteral() {
        return literal;
    }

    public Type getType() {
        return type;
    }

    @Override
    public <T> T accept(AllVisitors<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return literal;
    }

}
