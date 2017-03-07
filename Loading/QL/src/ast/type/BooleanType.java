package ast.type;

import ast.TypeVisitor;
import ui.Notifier;
import ui.field.Check;
import ui.field.Field;
import value.BoolValue;
import value.Value;

public class BooleanType extends Type {
	
    // TODO why can you not use the constructor of superclass Type directly
	public BooleanType(int line) {
		super("boolean", line);
	}

	@Override
	public void accept(TypeVisitor v) {
		v.visit(this);
		
	}

	@Override
	public Field getField(String name, Notifier notifier) {
		return new Check(name, notifier);
	}

	@Override
	public Value getValue() {
		return new BoolValue();
	}

}
