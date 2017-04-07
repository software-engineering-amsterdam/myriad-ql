package sc.ql.gui.values;

import java.math.BigDecimal;

public class MoneyValue extends Value {
	private final BigDecimal value;
	
	public MoneyValue(BigDecimal value) {
		this.value = value;
	}
	
	@Override
	public BigDecimal toMoney() {
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
	public MoneyValue add(MoneyValue value) {
        return new MoneyValue(value.toMoney().add(this.value));
    }
	
	@Override
	public Value divide(Value value) {
        return value.divide(this);
    }
	
	@Override
	public MoneyValue divide(MoneyValue value) {
        return new MoneyValue(value.toMoney().divide(this.value, 2, BigDecimal.ROUND_HALF_UP));
    }

	@Override
	public Value multiply(Value value) {
        return value.multiply(this);
    }
	
	@Override
	public MoneyValue multiply(MoneyValue value) {
        return new MoneyValue(value.toMoney().multiply(this.value));
    }
	
	@Override
	public Value subtract(Value value) {
        return value.subtract(this);
    }
	
	@Override
	public MoneyValue subtract(MoneyValue value) {
        return new MoneyValue(value.toMoney().subtract(this.value));
    }
	
	@Override
	public Value equals(Value value) {
        return value.equals(this);
    }
	
	@Override
	public BooleanValue equals(MoneyValue value) {
        return new BooleanValue(value.toMoney() == this.value);
    }
	
	@Override
	public Value equalsNot(Value value) {
        return value.equalsNot(this);
    }
	
	@Override
	public BooleanValue equalsNot(MoneyValue value) {
        return new BooleanValue(value.toMoney() != this.value);
    }
	
	@Override
	public Value greaterThen(Value value) {
        return value.greaterThen(this);
    }
	
	@Override
	public BooleanValue greaterThen(MoneyValue value) {
        return new BooleanValue(value.toMoney().floatValue() > this.value.floatValue());
    }
	
	@Override
	public Value greaterThenEqual(Value value) {
        return value.greaterThenEqual(this);
    }
	
	@Override
	public BooleanValue greaterThenEqual(MoneyValue value) {
        return new BooleanValue(value.toMoney().floatValue() >= this.value.floatValue());
    }
	
	@Override
	public Value lessThen(Value value) {
        return value.lessThen(this);
    }
	
	@Override
	public BooleanValue lessThen(MoneyValue value) {
        return new BooleanValue(value.toMoney().floatValue() < this.value.floatValue());
    }
	
	@Override
	public Value lessThenEqual(Value value) {
        return value.lessThenEqual(this);
    }
	
	@Override
	public BooleanValue lessThenEqual(MoneyValue value) {
        return new BooleanValue(value.toMoney().floatValue() <= this.value.floatValue());
    }

}
