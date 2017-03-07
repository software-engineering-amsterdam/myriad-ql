package ast.type;

import ast.TypeVisitor;
import ast.atom.Atom;
import ast.atom.StringAtom;
import ui.field.Field;
import ui.field.Text;

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
	public Atom getAtom() {
		return new StringAtom(0);
	}

}
