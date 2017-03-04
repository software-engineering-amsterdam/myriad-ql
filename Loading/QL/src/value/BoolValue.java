package value;

import ast.atom.BoolAtom;
import ast.type.BooleanType;
import ast.type.Type;
import ui.QControl;
import ui.field.Check;

public class BoolValue extends Value {
	
	private BoolAtom value;
	
	public BoolValue(boolean value) {
		this.value = new BoolAtom(value, 0); 
	}


	@Override
	public BoolAtom getValue() {
		return value;
	}

	@Override
	public Type getType() {
		return new BooleanType(0);
	}
	
}
