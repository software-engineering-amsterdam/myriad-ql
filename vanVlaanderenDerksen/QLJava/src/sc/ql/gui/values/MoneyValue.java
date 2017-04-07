package sc.ql.gui.values;

import java.math.BigDecimal;

public class MoneyValue extends Value {
	private final BigDecimal value;
	
	public MoneyValue(BigDecimal value) {
		this.value = value;
	}
	
	public BigDecimal getValue() {
		return value;
	}
	
	@Override
	public Value add(Value value) {
        return value.add(this);
    }
	
	@Override
	public MoneyValue add(MoneyValue value) {
        return new MoneyValue(this.value.add(value.getValue()));
    }
	
	@Override
	public Value divide(Value value) {
        return value.divide(this);
    }
	
	@Override
	public MoneyValue divide(MoneyValue value) {
        return new MoneyValue(this.value.divide(value.getValue()));
    }

	@Override
	public Value multiply(Value value) {
        return value.multiply(this);
    }
	
	@Override
	public MoneyValue multiply(MoneyValue value) {
        return new MoneyValue(this.value.multiply(value.getValue()));
    }
	
	@Override
	public Value subtract(Value value) {
        return value.subtract(this);
    }
	
	@Override
	public MoneyValue subtract(MoneyValue value) {
        return new MoneyValue(this.value.subtract(value.getValue()));
    }
	
	@Override
	public Value equals(Value value) {
        return value.equals(this);
    }
	
	@Override
	public BooleanValue equals(MoneyValue value) {
        return new BooleanValue(this.value == value.getValue());
    }
	
	@Override
	public Value equalsNot(Value value) {
        return value.equalsNot(this);
    }
	
	@Override
	public BooleanValue equalsNot(MoneyValue value) {
        return new BooleanValue(this.value != value.getValue());
    }
	
	@Override
	public Value greaterThen(Value value) {
        return value.greaterThen(this);
    }
	
	@Override
	public BooleanValue greaterThen(MoneyValue value) {
        return new BooleanValue(this.value.floatValue() > value.getValue().floatValue());
    }
	
	@Override
	public Value greaterThenEqual(Value value) {
        return value.greaterThenEqual(this);
    }
	
	@Override
	public BooleanValue greaterThenEqual(MoneyValue value) {
        return new BooleanValue(this.value.floatValue() >= value.getValue().floatValue());
    }
	
	@Override
	public Value lessThen(Value value) {
        return value.lessThen(this);
    }
	
	@Override
	public BooleanValue lessThen(MoneyValue value) {
        return new BooleanValue(this.value.floatValue() < value.getValue().floatValue());
    }
	
	@Override
	public Value lessThenEqual(Value value) {
        return value.lessThenEqual(this);
    }
	
	@Override
	public BooleanValue lessThenEqual(MoneyValue value) {
        return new BooleanValue(this.value.floatValue() <= value.getValue().floatValue());
    }

}
