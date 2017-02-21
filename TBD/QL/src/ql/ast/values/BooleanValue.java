package ql.ast.values;

/**
 * Created by Erik on 21-2-2017.
 */
public class BooleanValue extends Value<Boolean> {
    private final boolean value;

    public BooleanValue(boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Value and(Value other) {
        return other.andEval(this);
    }

    @Override
    protected Value andEval(BooleanValue other) {
        return new BooleanValue(other.getValue() && this.getValue());
    }

    @Override
    public Value or(Value other) {
        return other.orEval(this);
    }

    @Override
    protected Value orEval(BooleanValue other) {
        return new BooleanValue(other.getValue() || this.getValue());
    }

    @Override
    public Value not() {
        return new BooleanValue(!getValue());
    }
    
}
