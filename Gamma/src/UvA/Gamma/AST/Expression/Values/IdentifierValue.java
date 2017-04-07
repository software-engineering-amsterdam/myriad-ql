package UvA.Gamma.AST.Expression.Values;

import UvA.Gamma.AST.Expression.Identifier;
import UvA.Gamma.AST.Types.Type;
import UvA.Gamma.Visitors.Visitor;

/**
 * Created by Tjarco, 22-03-17.
 */
public class IdentifierValue extends Value<IdentifierValue> {
    private Value value;
    private Identifier identifier;

    /**
     * The Identifiervalue can be initialized without a value
     *
     * @param identifier The identifier string given in the QL
     */
    public IdentifierValue(String identifier) {
        this.identifier = new Identifier(identifier);
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public void updateValue(IdentifierValue value) {
        assert identifier != null;
        if (value.identifier.toString().equals(this.identifier.toString())) {
            this.value = value.value;
        }
    }

    @Override
    public Type getType() {
        assert value != null;
        return value.getType();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Value value() {
        assert value != null;
        return value.value();
    }

    @SuppressWarnings("unchecked")
    @Override
    public BooleanValue equals(IdentifierValue other) {
        assert value != null;
        assert this.getType().equals(other.getType());
        return this.value.equals(other.value);
    }

    @Override
    public String toString() {
        assert identifier != null;
        return identifier.toString();
    }
}
