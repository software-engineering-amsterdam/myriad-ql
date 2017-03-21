package UvA.Gamma.AST.Expression.Operands.BooleanOperands;

import UvA.Gamma.AST.Expression.Expression;
import UvA.Gamma.AST.Expression.Values.BooleanValue;
import UvA.Gamma.AST.Expression.Values.Value;

/**
 * Created by Tjarco, 21-03-17.
 */
public class And extends BooleanOperand {

    public And(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Value value() {
        assert left != null && right != null;
        return ((BooleanValue) left.value()).and((BooleanValue) right.value());
    }
}
