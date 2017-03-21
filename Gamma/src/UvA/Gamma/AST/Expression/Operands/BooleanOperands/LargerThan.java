package UvA.Gamma.AST.Expression.Operands.BooleanOperands;

import UvA.Gamma.AST.Expression.Expression;
import UvA.Gamma.AST.Expression.Operands.NumberOperands.NumberOperand;
import UvA.Gamma.AST.Expression.Values.NumberValue;
import UvA.Gamma.AST.Expression.Values.Value;

/**
 * Created by Tjarco, 21-03-17.
 */
public class LargerThan extends NumberOperand {

    public LargerThan(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Value value() {
        assert left != null && right != null;
        return ((NumberValue) left.value()).smallerThan(((NumberValue) right.value())).not();
    }
}
