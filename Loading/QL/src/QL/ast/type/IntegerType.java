package QL.ast.type;

import QL.ast.TypeVisitor;
import QL.ui.Notifier;
import QL.ui.field.Field;
import QL.ui.field.Number;
import QL.value.IntegerValue;
import QL.value.Value;

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
	public Field getField(String name, Notifier notifier, Value value) {
		return new Number(name, notifier, (IntegerValue) value);
	}

	@Override
	public Value getValue() {
		return new IntegerValue();
	}
	
}
