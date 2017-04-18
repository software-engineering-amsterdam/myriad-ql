package sc.ql.gui.values;

public class BooleanValue extends Value {
	private final Boolean value;
	
	public BooleanValue(Boolean value) {
		this.value = value;
	}
	
	@Override
	public Boolean toBoolean() {
		return value;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
	
	@Override
    public BooleanValue negate() {
        return new BooleanValue(!this.value);
    }
	
	@Override
	public Value and(Value value) {
        return value.and(this);
    }
	
	@Override
	public BooleanValue and(BooleanValue value) {
        return new BooleanValue(value.toBoolean() && this.value);
    }
	
	@Override
	public Value or(Value value) {
        return value.and(this);
    }
	
	@Override
	public BooleanValue or(BooleanValue value) {
        return new BooleanValue(value.toBoolean() || this.value);
    }
	
}
