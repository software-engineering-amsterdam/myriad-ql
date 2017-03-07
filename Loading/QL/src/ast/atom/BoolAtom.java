package ast.atom;

import ast.ExpressionVisitor;
import ast.type.Type;

public class BoolAtom extends Atom {

	private boolean atom;

	public BoolAtom(boolean atom, int line) {
		super(line);
		this.atom = atom;
	}

	public boolean getAtom() {
		return atom;
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}
}