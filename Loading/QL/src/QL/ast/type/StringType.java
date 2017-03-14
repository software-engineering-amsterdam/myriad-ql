package QL.ast.type;

import QL.ast.TypeVisitor;
import QL.ui.Notifier;
import QL.ui.field.Field;
import QL.ui.field.Text;
import QL.value.StringValue;
import QL.value.Value;

public class StringType extends Type {

	public StringType(int line) {
		super("string", line);
	}
	
	@Override
	public void accept(TypeVisitor v) {
		v.visit(this);
	}

	@Override
	public Field getField(String name, Notifier notifier, Value value) {
		return new Text(name, notifier, (StringValue) value);
	}

	@Override
	public Value getValue() {
		return new StringValue("");
	}

}
