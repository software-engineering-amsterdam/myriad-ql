package ast.type;

import ast.TypeVisitor;
import ast.atom.Atom;
import ast.atom.IntegerAtom;
import ui.field.Field;
import ui.field.Number;

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
	public Field getField(String name) {
		return new Number(name);
	}

	@Override
	public Atom getAtom() {
		return new IntegerAtom(0);
	}
	
}
