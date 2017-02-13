package ASTnodes.expressions.unaries;

import ASTnodes.types.IntegerType;
import ASTnodes.types.Type;
import ASTnodes.types.UndefinedType;
import ASTnodes.visitors.AllVisitors;
import ASTnodes.CodeLocation;
import ASTnodes.expressions.Expression;

/**
 * Created by LGGX on 10-Feb-17.
 */
public class Positive extends Unary {
    private final Expression expression;

    public Positive(Expression expression, CodeLocation location) {
        super(location);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public <T> T accept(AllVisitors<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "+" + expression;
    }


}
