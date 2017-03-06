package ast.type;

import ast.TypeVisitor;
import ast.atom.Atom;
import ast.atom.BoolAtom;
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
		return new Check(name);
	}

	@Override
	public Atom getAtom() {
		return new BoolAtom(0);
	}

}
