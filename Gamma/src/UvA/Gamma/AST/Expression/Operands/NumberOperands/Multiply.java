package UvA.Gamma.AST.Expression.Operands.NumberOperands;

import UvA.Gamma.AST.Expression.Expression;
import UvA.Gamma.AST.Expression.Values.NumberValue;
import UvA.Gamma.AST.Expression.Values.Value;

/**
 * Created by Tjarco, 21-03-17.
 */
public class Multiply extends NumberOperand {

    public Multiply(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Value value() {
        assert left != null && right != null;
        return ((NumberValue) left.value()).multiply((NumberValue) right.value());
    }
}
