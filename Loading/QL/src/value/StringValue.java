package value;


public class StringValue extends Value {
	
	private String value;
	
	public StringValue(String value) {
		this.value = value;
	}
	
	@Override
	public Object getValue() {
		return value;
	}
}
