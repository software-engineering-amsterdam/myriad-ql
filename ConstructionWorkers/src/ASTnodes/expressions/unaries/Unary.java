package ASTnodes.expressions.unaries;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.Expression;
import ASTnodes.types.Type;

/**
 * Created by LGGX on 10-Feb-17.
 */
public abstract class Unary extends Expression {

    private Expression expression;

    public Unary(CodeLocation location) {
        super(location);
    }

    public Expression getExpression() {
        return expression;
    }

}
