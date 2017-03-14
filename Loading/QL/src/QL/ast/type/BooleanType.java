package QL.ast.type;

import QL.ast.TypeVisitor;
import QL.ui.Notifier;
import QL.ui.field.Check;
import QL.ui.field.Field;
import QL.value.BoolValue;
import QL.value.Value;

public class BooleanType extends Type {
	
	public BooleanType(int line) {
		super("boolean", line);
	}

	@Override
	public <T> T accept(TypeVisitor<T> v) {
		return v.visit(this);	
	}

	@Override
	public Field getField(String name, Notifier notifier, Value value) {
		return new Check(name, notifier, (BoolValue) value);
	}

}
