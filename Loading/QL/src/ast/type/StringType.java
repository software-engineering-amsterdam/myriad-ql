package ast.type;

import ast.TypeVisitor;
import ui.field.Field;
import ui.field.Text;
import value.StringValue;
import value.Value;

public class StringType extends Type {

	public StringType(int line) {
		super("string", line);
	}
	
	@Override
	public void accept(TypeVisitor v) {
		v.visit(this);
	}

	@Override
	public Field getField(String name) {
		return new Text(name);
	}

	@Override
	public Value getValue() {
		return new StringValue();
	}

}
