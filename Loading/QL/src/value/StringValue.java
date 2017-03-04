package value;

import ast.atom.StringAtom;
import ast.type.StringType;
import ast.type.Type;
import ui.QControl;
import ui.field.Text;

public class StringValue extends Value {
	
	private StringAtom value;
	
	public StringValue(String value) {
		this.value = new StringAtom(value, 0);
	}
	
	
	@Override
	public StringAtom getValue() {
		return value;
	}

	@Override
	public Type getType() {
		return new StringType(0);
	}

}
