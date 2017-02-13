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

    public BOOLEAN(Boolean literal) {
        super(null);
        this.literal = literal;
        this.type = new BooleanType();
    }

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
    public boolean equals(Object obj) {
        if (!(obj instanceof BOOLEAN)) {
            return false;
        }
        BOOLEAN lit2 = (BOOLEAN) obj;
        return literal.equals(lit2.getLiteral());
    }

    @Override
    public int hashCode() {
        return literal.hashCode();
    }

    @Override
    public <T, U> T accept(AllVisitors<T, U> visitor, U context) {
        return visitor.visit(this, context);
    }

    @Override
    public String toString() {
        return literal.toString();
    }
}
