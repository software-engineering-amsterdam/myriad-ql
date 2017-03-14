package QL.ast.type;

import QL.ast.TypeVisitor;
import QL.ui.Notifier;
import QL.ui.field.Field;
import QL.ui.field.Number;
import QL.value.IntegerValue;
import QL.value.Value;

public class IntegerType extends Type {

	public IntegerType(int line) {
		super("integer", line);
	}
	
	@Override
	public <T> T accept(TypeVisitor<T> v) {
		return v.visit(this);
	}

	@Override
	public Field getField(String name, Notifier notifier, Value value) {
		return new Number(name, notifier, (IntegerValue) value);
	}
	
}
