package UvA.Gamma.AST.Expression.Operands.BooleanOperands;

import UvA.Gamma.AST.Expression.Expression;
import UvA.Gamma.AST.Types.BooleanType;
import UvA.Gamma.AST.Types.DecimalType;
import UvA.Gamma.Visitors.Visitor;

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
    public void accept(Visitor visitor) {
        left.accept(visitor);
        right.accept(visitor);
        visitor.visit(this);
    }

    public abstract boolean validateTypes();

    protected boolean validateBools() {
        return left.value().conformsToType(new BooleanType()) &&
                right.value().conformsToType(new BooleanType());
    }

    protected boolean validateNumbers() {
        return left.value().conformsToType(new DecimalType()) &&
                right.value().conformsToType(new DecimalType());
    }

    @Override
    public String toString() {
        return left.value() + " " + right.value();
    }
}
