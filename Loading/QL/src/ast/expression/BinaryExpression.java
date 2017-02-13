package ast.expression;

import ast.atom.Atom;

public abstract class BinaryExpression extends Expression {

	final private Atom lhs;
	final private Atom rhs;
	// protected boolean eval;

	public BinaryExpression(Atom lhs, Atom rhs) {
		// TODO fix eval check
		this.lhs = lhs;
		this.rhs = rhs;
		// this.eval = lhs == rhs;
	}

//	public boolean isEval() {
//		return eval;
//	}

}
