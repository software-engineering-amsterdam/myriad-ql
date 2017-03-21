package sc.ql.gui.values;

public class BooleanValue extends Value {
	private final Boolean value;
	
	public BooleanValue(Boolean value) {
		this.value = value;
	}
	
	public Boolean getValue() {
		return value;
	}
	
	@Override
    public BooleanValue negate() {
        return new BooleanValue(!this.value);
    }
	
}
