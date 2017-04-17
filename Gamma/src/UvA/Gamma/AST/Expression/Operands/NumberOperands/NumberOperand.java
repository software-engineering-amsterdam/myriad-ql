package UvA.Gamma.AST.Expression.Operands.NumberOperands;

import UvA.Gamma.AST.Expression.Expression;
import UvA.Gamma.AST.Types.DecimalType;
import UvA.Gamma.Visitors.Visitor;

/**
 * Created by Tjarco, 21-03-17.
 */
public abstract class NumberOperand extends Expression {
    protected Expression left;
    protected Expression right;

    public NumberOperand(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void accept(Visitor visitor) {
        left.accept(visitor);
        right.accept(visitor);
        visitor.visit(this);
    }

    public boolean validateTypes() {
        return left.value().conformsToType(new DecimalType()) &&
                right.value().conformsToType(new DecimalType());
    }

    @Override
    public String toString() {
        return this.left.toString() + this.right.toString();
    }
}
