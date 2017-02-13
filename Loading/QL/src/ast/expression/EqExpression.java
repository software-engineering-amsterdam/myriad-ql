package ast.expression;

import ast.atom.Atom;

public class EqExpression extends BinaryExpression {
	
	public EqExpression(Atom lhs, Atom rhs) {
		super(lhs, rhs);
		// this.eval = lhs == rhs;
	}
}
