package value;

import ast.type.Type;

public class EmptyValue extends Value {
	
	@Override
	public Object getValue() {
		return null;
	}

	@Override
	public Type getType() {
		return null;
	}

}
