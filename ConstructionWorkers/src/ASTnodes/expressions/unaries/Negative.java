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
public class Negative extends Unary {

    private final Expression expression;

    public Negative(Expression expression, CodeLocation location) {
        super(location);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public <T, U> T accept(AllVisitors<T, U> visitor, U context) {
        return visitor.visit(this, context);
    }

    @Override
    public String toString() {
        return "-" + expression;
    }

    @Override
    public Type inferType(Type exprType) {
        if (exprType.equals(new IntegerType())) {
            return exprType;
        }

        return new UndefinedType();
    }
}
