package value;

import ast.atom.BoolAtom;
import ast.type.BooleanType;
import ast.type.Type;

public class BoolValue extends Value {
	
	private BoolAtom value;
	
	public BoolValue(boolean value) {
		this.value = new BoolAtom(value, 0);
	}

	@Override
	public BoolAtom getValue() {
		return value;
	}
}
