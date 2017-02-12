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

    public STRING(String literal) {
        super(null);
        this.literal = literal;
        this.type = new StringType();
    }

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
    public boolean equals(Object obj) {
        if (!(obj instanceof STRING)) {
            return false;
        }
        STRING lit2 = (STRING) obj;
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
        return literal;
    }

}
