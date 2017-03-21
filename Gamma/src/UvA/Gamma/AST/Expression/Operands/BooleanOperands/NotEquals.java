package UvA.Gamma.AST.Expression.Operands.BooleanOperands;

import UvA.Gamma.AST.Expression.Expression;
import UvA.Gamma.AST.Expression.Values.BooleanValue;
import UvA.Gamma.AST.Expression.Values.Value;

/**
 * Created by Tjarco, 21-03-17.
 */
public class NotEquals extends Equals {

    public NotEquals(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Value value() {
        return ((BooleanValue) super.value()).not();
    }
}
