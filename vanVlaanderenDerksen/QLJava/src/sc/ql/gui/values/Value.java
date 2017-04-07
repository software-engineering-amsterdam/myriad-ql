package sc.ql.gui.values;

public abstract class Value {
	
	public Boolean getValue() {
		return null;
	}
	
	public String toString() {
		return null;
	}
	
	public Boolean isEmptyValue() {
		return false;
	}
	
	public Boolean isTrue() {
		return false;
	}
	
	public BooleanValue negate() {
        return null;
    }
	
	public Value add(Value value) { 
		return null;
    }
	
	public IntegerValue add(IntegerValue value) {
        return null;
    }
	
	public MoneyValue add(MoneyValue value) {
        return null;
    }
	
	public Value divide(Value value) {
        return null;
    }
	
	public IntegerValue divide(IntegerValue value) {
        return null;
    }
	
	public MoneyValue divide(MoneyValue value) {
        return null;
    }
	
	public Value multiply(Value value) {
        return null;
    }
	
	public IntegerValue multiply(IntegerValue value) {
        return null;
    }
	
	public MoneyValue multiply(MoneyValue value) {
        return null;
    }
	
	public Value subtract(Value value) {
        return null;
    }
	
	public IntegerValue subtract(IntegerValue value) {
        return null;
    }
	
	public MoneyValue subtract(MoneyValue value) {
        return null;
    }

	public Value and(Value value) {
        return null;
    }
	
	public BooleanValue and(BooleanValue value) {
        return null;
    }
	
	public Value or(Value value) {
        return null;
    }
	
	public BooleanValue or(BooleanValue value) {
        return null;
    }
	
	public Value equals(Value value) {
        return null;
    }
	
	public BooleanValue equals(IntegerValue value) {
        return null;
    }
	
	public BooleanValue equals(MoneyValue value) {
        return null;
    }
	
	public BooleanValue equals(StringValue value) {
        return null;
    }
	
	public Value equalsNot(Value value) {
        return null;
    }
	
	public BooleanValue equalsNot(IntegerValue value) {
        return null;
    }
	
	public BooleanValue equalsNot(MoneyValue value) {
        return null;
    }
	
	public BooleanValue equalsNot(StringValue value) {
        return null;
    }
	
	public Value greaterThen(Value value) {
        return null;
    }
	
	public BooleanValue greaterThen(IntegerValue value) {
        return null;
    }
	
	public BooleanValue greaterThen(MoneyValue value) {
        return null;
    }
	
	public Value greaterThenEqual(Value value) {
        return null;
    }
	
	public BooleanValue greaterThenEqual(IntegerValue value) {
        return null;
    }
	
	public BooleanValue greaterThenEqual(MoneyValue value) {
        return null;
    }
	
	public Value lessThen(Value value) {
        return null;
    }
	
	public BooleanValue lessThen(IntegerValue value) {
        return null;
    }
	
	public BooleanValue lessThen(MoneyValue value) {
        return null;
    }
	
	public Value lessThenEqual(Value value) {
        return null;
    }
	
	public BooleanValue lessThenEqual(IntegerValue value) {
        return null;
    }
	
	public BooleanValue lessThenEqual(MoneyValue value) {
        return null;
    }
	
}
