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
		// TODO Auto-generated method stub
		return new Text(name);
	}

}
