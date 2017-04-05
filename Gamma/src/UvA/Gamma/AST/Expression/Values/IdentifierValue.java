package UvA.Gamma.AST.Expression.Values;

import UvA.Gamma.AST.Types.Type;
import UvA.Gamma.Visitors.Visitor;

/**
 * Created by Tjarco, 22-03-17.
 */
public class IdentifierValue extends Value<IdentifierValue> {
    private Value value;
    private String identifier;

    public IdentifierValue(String identifier, Value value) {
        this.identifier = identifier;
        this.value = value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public void updateValue(IdentifierValue value) {
        if (value.identifier.equals(this.identifier)) {
            this.value = value.value;
        }
    }

    @Override
    public Type getType() {
        return value.getType();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Value value() {
        return value.value();
    }

    @SuppressWarnings("unchecked")
    @Override
    public BooleanValue equals(IdentifierValue other) {
        assert this.getType().equals(other.getType());
        return this.value.equals(other.value);
    }
}
