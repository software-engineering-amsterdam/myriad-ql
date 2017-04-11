package UvA.Gamma.Visitors;

import UvA.Gamma.AST.Computed;
import UvA.Gamma.AST.Expression.Identifier;
import UvA.Gamma.AST.Expression.Values.IdentifierValue;
import UvA.Gamma.AST.Expression.Values.Value;
import UvA.Gamma.AST.Types.Type;

/**
 * Created by Tjarco, 11-04-17.
 */
public class IdentifierInitVisitor extends BaseVisitor {
    private Value value;
    private Identifier identifier;

    public IdentifierInitVisitor(Identifier identifier, Type type) {
        this.value = type.defaultValue();
        this.identifier = identifier;
    }

    @Override
    public void visit(IdentifierValue value) {
        if (this.identifier.toString().equals(value.toString())) {
            value.setValue(this.value);
        }
    }

    @Override
    public void visit(Computed computed) {
        System.out.println(computed.expression.value());
    }
}
