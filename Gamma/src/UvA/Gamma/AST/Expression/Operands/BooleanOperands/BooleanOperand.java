package UvA.Gamma.AST.Expression.Operands.BooleanOperands;

import UvA.Gamma.AST.Expression.Expression;

/**
 * Created by Tjarco, 21-03-17.
 */
public abstract class BooleanOperand extends Expression {
    protected Expression left;
    protected Expression right;

    public BooleanOperand(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return left.value() + " " + right.value();
    }
}
