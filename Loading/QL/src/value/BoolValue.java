package value;


public class BoolValue extends Value {
	
	private boolean value;
	
	public BoolValue(boolean value) {
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}
}
