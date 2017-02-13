package ASTnodes.expressions.unaries;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.Expression;
import ASTnodes.types.*;
import ASTnodes.visitors.AllVisitors;

/**
 * Created by LGGX on 10-Feb-17.
 */
public class Parenthesis extends Unary {

    private final Expression expression;

    public Parenthesis(Expression expression, CodeLocation location) {
        super(location);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return "(" + expression + ")";
    }

    @Override
    public <T> T accept(AllVisitors<T> visitor) {
        return visitor.visit(this);
    }

}
