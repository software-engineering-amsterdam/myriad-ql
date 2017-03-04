package value;

import ast.atom.IntegerAtom;
import ast.atom.StringAtom;
import ast.type.IntegerType;
import ast.type.Type;
import ui.QControl;
import ui.field.Text;
import ui.field.Number;

public class IntegerValue extends Value {
	
	private IntegerAtom value;
	private String name;
	
	public IntegerValue(String name, Integer value) {
		this.value = new IntegerAtom(value, 0); // TODO fix line numbers
		this.name = name;
	}
	
	@Override
	public IntegerAtom getValue() {
		return value;
	}

	@Override
	public Type getType() {
		return new IntegerType(0);
	}

	@Override
	public QControl getField() {
		return  new QControl(new Number(name));
	}
}

