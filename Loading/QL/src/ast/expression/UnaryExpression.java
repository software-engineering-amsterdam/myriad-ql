package ast.expression;

import ast.Visitor;

public abstract class UnaryExpression extends Expression {

    private Expression lhs;

	public UnaryExpression(Expression lhs, int line) {
		super(line);
		this.lhs = lhs;
	}

	public Expression getLhs() {
		return lhs;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
		
	}
}
