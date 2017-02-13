package ASTnodes.expressions.literals;

import ASTnodes.types.Type;
import ASTnodes.CodeLocation;
import ASTnodes.types.BooleanType;
import ASTnodes.visitors.AllVisitors;

/**
 * Created by LGGX on 10-Feb-17.
 */
public class BOOLEAN extends Literal {

    private final Type type;
    private final Boolean literal;

    public BOOLEAN(Boolean literal, CodeLocation location) {
        super(location);
        this.literal = literal;
        this.type = new BooleanType(location);
    }

    @Override
    public Boolean getLiteral() {
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
        return literal.toString();
    }
}
