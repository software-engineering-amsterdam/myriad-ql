package ast.expression;

import ast.Visitor;

public abstract class BinaryExpression extends Expression {

	private Expression lhs;
	private Expression rhs;
	
	// TODO is this preferred over a constructor with lhs and rhs?
	public BinaryExpression(Expression lhs, Expression rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
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
