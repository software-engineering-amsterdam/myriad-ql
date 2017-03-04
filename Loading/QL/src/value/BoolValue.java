package value;

import ast.atom.BoolAtom;
import ast.type.BooleanType;
import ast.type.Type;
import ui.QControl;
import ui.field.Check;

public class BoolValue extends Value {
	
	// private Boolean value;
	private BoolAtom value;
	private String name;
	
	public BoolValue(String name, boolean value) {
		this.value = new BoolAtom(value, 0); 
		this.name = name;
	}


	@Override
	public BoolAtom getValue() {
		return value;
	}

	@Override
	public Type getType() {
		return new BooleanType(0);
	}
	
	public String getName() {
		return name;
	}

	@Override
	public QControl getField() {
		return new QControl(new Check(name));
	}
}
