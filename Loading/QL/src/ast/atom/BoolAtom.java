package ast.atom;

import ast.ExpressionVisitor;
import ast.type.Type;

public class BoolAtom extends Atom {
    

	public BoolAtom(int line) {
		super(line);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}
}