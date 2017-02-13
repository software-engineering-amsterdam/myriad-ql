package ast.expresssion;

import ast.atom.Atom;

public class EqExpression extends Expression {
	
	public EqExpression(Atom lhs, Atom rhs) {
		super(lhs, rhs);
		// this.eval = lhs == rhs;
	}
}
