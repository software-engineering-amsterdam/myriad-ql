package sc.ql.gui.values;

public class IntegerValue extends Value {
	private final Integer value;
	
	public IntegerValue(Integer value) {
		this.value = value;
	}
	
	@Override
	public Integer toInteger() {
		return value;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
	
	@Override
	public Value add(Value value) {
        return value.add(this);
    }
	
	@Override
	public IntegerValue add(IntegerValue value) {
        return new IntegerValue(this.value + value.toInteger());
    }
	
	@Override
	public Value divide(Value value) {
        return value.divide(this);
    }
	
	@Override
	public IntegerValue divide(IntegerValue value) {
        return new IntegerValue(this.value / value.toInteger());
    }
	
	@Override
	public Value multiply(Value value) {
        return value.multiply(this);
    }
	
	@Override
	public IntegerValue multiply(IntegerValue value) {
        return new IntegerValue(this.value * value.toInteger());
    }
	
	@Override
	public Value subtract(Value value) {
        return value.subtract(this);
    }
	
	@Override
	public IntegerValue subtract(IntegerValue value) {
        return new IntegerValue(this.value - value.toInteger());
    }
	
	@Override
	public Value equals(Value value) {
        return value.equals(this);
    }
	
	@Override
	public BooleanValue equals(IntegerValue value) {
        return new BooleanValue(value.toInteger() == this.value);
    }
	
	@Override
	public Value equalsNot(Value value) {
        return value.equalsNot(this);
    }
	
	@Override
	public BooleanValue equalsNot(IntegerValue value) {
        return new BooleanValue(value.toInteger() != this.value);
    }
	
	@Override
	public Value greaterThen(Value value) {
        return value.greaterThen(this);
    }
	
	@Override
	public BooleanValue greaterThen(IntegerValue value) {
        return new BooleanValue(this.value > value.toInteger());
    }
	
	@Override
	public Value greaterThenEqual(Value value) {
        return value.greaterThenEqual(this);
    }
	
	@Override
	public BooleanValue greaterThenEqual(IntegerValue value) {
        return new BooleanValue(this.value >= value.toInteger());
    }
	
	@Override
	public Value lessThen(Value value) {
        return value.lessThen(this);
    }
	
	@Override
	public BooleanValue lessThen(IntegerValue value) {
        return new BooleanValue(this.value < value.toInteger());
    }
	
	@Override
	public Value lessThenEqual(Value value) {
        return value.lessThenEqual(this);
    }
	
	@Override
	public BooleanValue lessThenEqual(IntegerValue value) {
        return new BooleanValue(this.value <= value.toInteger());
    }
	
}
