package value;

import ast.type.StringType;
import ast.type.Type;

public class StringValue extends Value {
	
	private String value;
	
	public StringValue(String value) {
		this.value = value;
	}
	
	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Type getType() {
		return new StringType(0);
	}
}
