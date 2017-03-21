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
	public Value add(Value add) {
        return add.add(this);
    }
	
	@Override
	public MoneyValue add(MoneyValue add) {
        return new MoneyValue(this.value.add(add.getValue()));
    }
	
}
