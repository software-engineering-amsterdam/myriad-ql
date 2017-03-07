package ast.atom;

import ast.ExpressionVisitor;
import ast.type.Type;

public class IntegerAtom extends Atom {
		

	public IntegerAtom(int line) {
		super(line);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}
}