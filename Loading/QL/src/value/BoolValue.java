package value;

import ast.type.BooleanType;
import ast.type.Type;

public class BoolValue extends Value {
	
	private boolean value;
	
	public BoolValue(boolean value) {
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Type getType() {
		return new BooleanType(0);
	}
}
