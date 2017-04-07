package sc.ql.gui.values;

import java.math.BigDecimal;

public class EmptyValue extends Value {

	@Override
	public Boolean toBoolean() {
		return false;
	}
	
	@Override
	public Integer toInteger() {
		return 0;
	}
	
	@Override
	public BigDecimal toMoney() {
		return new BigDecimal(0);
	}
	
	@Override
	public String toString() {
		return "";
	}
	
}
