package ast.atom;

import ast.ExpressionVisitor;

public class StringAtom extends Atom {
    
	public StringAtom(int line) {
		super(line);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}

}