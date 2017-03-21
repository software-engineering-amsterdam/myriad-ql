package sc.ql.gui.values;

public class StringValue extends Value {
	private final String value;
	
	public StringValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
