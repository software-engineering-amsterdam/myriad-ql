package ast.type;

import ast.TypeVisitor;
import ui.field.Field;
import ui.field.Number;
import value.IntegerValue;
import value.Value;

public class IntegerType extends Type {

    // TODO why can you not use the constructor of superclass Type directly
	public IntegerType(int line) {
		super("integer", line);
	}
	
	@Override
	public void accept(TypeVisitor v) {
		v.visit(this);
	}

	@Override
	public Field getField(String name) {
		return new Number(name);
	}

	@Override
	public Value getValue() {
		return new IntegerValue();
	}
	
}
