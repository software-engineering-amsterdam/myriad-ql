package QL.ast.atom;

import QL.ast.ExpressionVisitor;

public class StringAtom extends Atom {

	private final String atom;
    
	public StringAtom(String atom, int line) {
		super(line);
		this.atom = atom;
	}

	public String getAtom() {
		return atom;
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}

}