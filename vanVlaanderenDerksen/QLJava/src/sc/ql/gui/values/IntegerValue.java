package sc.ql.gui.values;

public class IntegerValue extends Value {
	private final Integer value;
	
	public IntegerValue(Integer value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return value;
	}
	
	@Override
	public Value add(Value add) {
        return add.add(this);
    }
	
	@Override
	public IntegerValue add(IntegerValue add) {
        return new IntegerValue(this.value + add.getValue());
    }
	
}
