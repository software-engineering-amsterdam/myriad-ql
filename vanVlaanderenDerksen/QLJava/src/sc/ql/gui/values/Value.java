package sc.ql.gui.values;

import java.math.BigDecimal;

public abstract class Value {
	
	public Boolean toBoolean() {
		return null;
	}
	
	public Integer toInteger() {
		return null;
	}
	
	public BigDecimal toMoney() {
		return null;
	}
	
	public String toString() {
		return null;
	}
	
	public Value negate() {
        return new EmptyValue();
    }
	
	public Value add(Value value) { 
		return new EmptyValue();
    }
	
	public Value add(IntegerValue value) {
        return new EmptyValue();
    }
	
	public Value add(MoneyValue value) {
        return new EmptyValue();
    }
	
	public Value divide(Value value) {
        return new EmptyValue();
    }
	
	public Value divide(IntegerValue value) {
        return new EmptyValue();
    }
	
	public Value divide(MoneyValue value) {
        return new EmptyValue();
    }
	
	public Value multiply(Value value) {
        return new EmptyValue();
    }
	
	public Value multiply(IntegerValue value) {
        return new EmptyValue();
    }
	
	public Value multiply(MoneyValue value) {
        return new EmptyValue();
    }
	
	public Value subtract(Value value) {
        return new EmptyValue();
    }
	
	public Value subtract(IntegerValue value) {
        return new EmptyValue();
    }
	
	public Value subtract(MoneyValue value) {
        return new EmptyValue();
    }

	public Value and(Value value) {
        return new EmptyValue();
    }
	
	public Value and(BooleanValue value) {
        return new EmptyValue();
    }
	
	public Value or(Value value) {
        return new EmptyValue();
    }
	
	public Value or(BooleanValue value) {
        return new EmptyValue();
    }
	
	public Value equals(Value value) {
        return new EmptyValue();
    }
	
	public Value equals(IntegerValue value) {
        return new EmptyValue();
    }
	
	public Value equals(MoneyValue value) {
        return new EmptyValue();
    }
	
	public Value equals(StringValue value) {
        return new EmptyValue();
    }
	
	public Value equalsNot(Value value) {
        return new EmptyValue();
    }
	
	public Value equalsNot(IntegerValue value) {
        return new EmptyValue();
    }
	
	public Value equalsNot(MoneyValue value) {
        return new EmptyValue();
    }
	
	public Value equalsNot(StringValue value) {
        return new EmptyValue();
    }
	
	public Value greaterThen(Value value) {
        return new EmptyValue();
    }
	
	public Value greaterThen(IntegerValue value) {
        return new EmptyValue();
    }
	
	public Value greaterThen(MoneyValue value) {
        return new EmptyValue();
    }
	
	public Value greaterThenEqual(Value value) {
        return new EmptyValue();
    }
	
	public Value greaterThenEqual(IntegerValue value) {
        return new EmptyValue();
    }
	
	public Value greaterThenEqual(MoneyValue value) {
        return new EmptyValue();
    }
	
	public Value lessThen(Value value) {
        return new EmptyValue();
    }
	
	public Value lessThen(IntegerValue value) {
        return new EmptyValue();
    }
	
	public Value lessThen(MoneyValue value) {
        return new EmptyValue();
    }
	
	public Value lessThenEqual(Value value) {
        return new EmptyValue();
    }
	
	public Value lessThenEqual(IntegerValue value) {
        return new EmptyValue();
    }
	
	public Value lessThenEqual(MoneyValue value) {
        return new EmptyValue();
    }
	
}
