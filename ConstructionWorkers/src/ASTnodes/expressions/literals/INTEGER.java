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
    public <T> T accept(AllVisitors<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return literal.toString();
    }

}
