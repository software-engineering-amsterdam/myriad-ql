package QL.ast.type;

import QL.ast.TypeVisitor;
import QL.ui.Notifier;
import QL.ui.field.Check;
import QL.ui.field.Field;
import QL.value.BoolValue;
import QL.value.Value;

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
	public Field getField(String name, Notifier notifier, Value value) {
		return new Check(name, notifier, (BoolValue) value);
	}

	@Override
	public Value getValue() {
		return new BoolValue();
	}

}
