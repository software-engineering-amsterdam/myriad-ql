package ast.type;

import ast.TypeVisitor;
import ui.field.Check;
import ui.field.Field;

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
	public Field getField(String name) {
		// TODO Auto-generated method stub
		return new Check(name);
	}

}
