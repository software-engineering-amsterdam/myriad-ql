package QL.ast.atom;

import QL.ast.ExpressionVisitor;

public class IntegerAtom extends Atom {
		
	private int atom;

	public IntegerAtom(int atom, int line) {
		super(line);
		this.atom = atom;
	}

	public int getAtom() {
		return atom;
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}
}