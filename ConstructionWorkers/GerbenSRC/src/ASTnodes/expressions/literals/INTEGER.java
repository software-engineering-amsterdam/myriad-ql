package ASTnodes.expressions.literals;

import ASTnodes.types.Type;
import ASTnodes.CodeLocation;
import ASTnodes.types.IntegerType;
import ASTnodes.visitors.AllVisitors;

/**
 * Created by LGGX on 10-Feb-17.
 */
public class INTEGER extends Literal {

    private final Type type;
    private final Integer literal;

    public INTEGER(Integer literal) {
        super(null);
        this.literal = literal;
        this.type = new IntegerType();
    }

    public INTEGER(Integer literal, CodeLocation location) {
        super(location);
        this.literal = literal;
        this.type = new IntegerType(location);
    }

    @Override
    public Integer getLiteral() {
        return literal;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof INTEGER)) {
            return false;
        }
        INTEGER lit2 = (INTEGER) obj;
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
