package ast.expression;

import ast.atom.Atom;

public abstract class BinaryExpression extends Expression {

	private Atom lhs;
	private Atom rhs;
	// protected boolean eval;
	
	// TODO is this preferred over a constructor with lhs and rhs?
	public BinaryExpression() {
//		this.lhs = lhs;
//		this.rhs = rhs;
	}
	
	public void setElements(Atom lhs, Atom rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
//	public boolean isEval() {
//		return eval;
//	}

}
