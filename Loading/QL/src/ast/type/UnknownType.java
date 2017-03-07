package ast.type;

import ast.TypeVisitor;
import ast.atom.Atom;
import ui.field.Field;

public class UnknownType extends Type {

	public UnknownType(int line) {
		super("unknown", line);
	}

	@Override
	public Field getField(String name) {
		return null;
	}

	@Override
	public void accept(TypeVisitor v) {
		v.visit(this);
		
	}

	@Override
	public Atom getAtom() {
		return null;
	}

}
