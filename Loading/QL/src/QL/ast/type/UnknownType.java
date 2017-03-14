package QL.ast.type;

import QL.ast.TypeVisitor;
import QL.ui.Notifier;
import QL.ui.field.Field;
import QL.value.Value;

public class UnknownType extends Type {

	public UnknownType(int line) {
		super("unknown", line);
	}

	@Override
	public Field getField(String name, Notifier notifier, Value value) {
		return null;
	}

	@Override
	public <T> T accept(TypeVisitor<T> v) {
		return v.visit(this);
		
	}

}
