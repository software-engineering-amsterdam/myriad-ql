package value;

import ast.type.IntegerType;
import ast.type.Type;

public class IntegerValue extends Value {
	
	private int value;
	
	public IntegerValue(int value) {
		this.value = value;
	}
	
	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Type getType() {
		return new IntegerType();
	}
}
