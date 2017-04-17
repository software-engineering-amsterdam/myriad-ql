package UvA.Gamma.AST.Expression.Operands.BooleanOperands;

import UvA.Gamma.AST.Expression.Expression;
import UvA.Gamma.AST.Expression.Values.BooleanValue;
import UvA.Gamma.AST.Expression.Values.Value;
import UvA.Gamma.Visitors.Visitor;

/**
 * Created by Tjarco, 22-03-17.
 */
public class Not extends Expression {
    private Expression value;

    public Not(Expression value) {
        this.value = value;
    }

    @Override
    public Value value() {
        assert value != null;
        return ((BooleanValue) value.value()).not();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "!" + value.toString();
    }
}
