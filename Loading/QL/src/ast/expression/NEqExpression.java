package ast.expression;

import ast.atom.Atom;

public class NEqExpression extends BinaryExpression {

	public NEqExpression(Atom lhs, Atom rhs) {
		super(lhs, rhs);
	}

}
