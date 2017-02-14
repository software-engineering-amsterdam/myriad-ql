package ast.expression;

import ast.Visitor;
import ast.atom.Atom;

public class EqExpression extends BinaryExpression {
	@Override
	public Atom evaluate() {
		if (getLhs() == null || getRhs() == null) {
			// throw exception!
		}
		return getLhs(); // TODO implement
		// return getLhs().eq(getRhs());
	}
	@Override
	public void accept(Visitor v) {
		v.visit(this);		
	}
	
}
