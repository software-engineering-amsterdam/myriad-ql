package ASTnodes.expressions.literals;

import ASTnodes.expressions.Expression;
import ASTnodes.types.Type;
import ASTnodes.CodeLocation;

/**
 * Created by LGGX on 10-Feb-17.
 */

public abstract class Literal extends Expression {

    private Type type;

    public Literal(CodeLocation location) {
        super(location);
    }

    public Type getType() {
        return type;
    }

    public Object getLiteral() {
        return null;
    }

}
