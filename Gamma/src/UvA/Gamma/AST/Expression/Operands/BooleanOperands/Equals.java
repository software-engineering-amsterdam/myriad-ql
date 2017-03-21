package UvA.Gamma.AST.Expression.Operands.BooleanOperands;

import UvA.Gamma.AST.Expression.Expression;
import UvA.Gamma.AST.Expression.Values.Value;

/**
 * Created by Tjarco, 21-03-17.
 */
public class Equals extends BooleanOperand {

    public Equals(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    @SuppressWarnings("unchecked") //Type equality is enforced by the grammar
    public Value value() {
        assert left != null && right != null;
        assert left.value().getType().equalsType(right.value().getType());
        return left.value().equals(right.value());
    }
}
