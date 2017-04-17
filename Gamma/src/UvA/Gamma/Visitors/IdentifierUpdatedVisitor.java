package UvA.Gamma.Visitors;

import UvA.Gamma.AST.Computed;
import UvA.Gamma.AST.Condition;
import UvA.Gamma.AST.Expression.Values.IdentifierValue;

/**
 * Created by Tjarco, 05-04-17.
 */
public class IdentifierUpdatedVisitor extends BaseVisitor {
    private IdentifierValue updatedValue;

    public IdentifierUpdatedVisitor(IdentifierValue updatedValue) {
        this.updatedValue = updatedValue;
    }


    @Override
    public void visit(Computed computed) {
        computed.updateValue();
    }

    @Override
    public void visit(Condition condition) {
        System.out.println("Condition is now: " + condition.evaluateExpression());
    }

    @Override
    public void visit(IdentifierValue value) {
        assert updatedValue != null;
        value.updateValue(updatedValue);
        System.out.println("updated value: " + value.value() + " from " + updatedValue.value());
    }
}
