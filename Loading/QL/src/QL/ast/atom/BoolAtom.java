package QL.ast.atom;

import QL.ast.ExpressionVisitor;

public class BoolAtom extends Atom {

	private final boolean atom;

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