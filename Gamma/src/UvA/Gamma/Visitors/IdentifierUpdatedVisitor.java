package UvA.Gamma.Visitors;

import UvA.Gamma.AST.Expression.Expression;
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
    public void visit(Expression expression) {
        System.out.println(expression.value());
    }

    @Override
    public void visit(IdentifierValue value) {
        assert updatedValue != null;
        value.updateValue(updatedValue);
        System.out.println("updated value: " + value.value() + " from " + updatedValue.value());
    }
}
