package ast.expression;

import ast.Visitor;
import ast.atom.Atom;

public abstract class BinaryExpression extends Expression {

	private Expression lhs;
	private Expression rhs;
	// protected boolean eval;
	
	// TODO is this preferred over a constructor with lhs and rhs?
	public BinaryExpression() {
	}
	
	public BinaryExpression setElements(Expression lhs, Expression rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
		return this;
	}
	
	public Expression getLhs() {
		return lhs;
	}
	
	public Expression getRhs() {
		return rhs;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
