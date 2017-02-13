package ast;

import ast.expression.Expression;

public class Statement implements Node {
	
	// private boolean evaluate; // TODO change name
	private Expression expression; // TODO change name
	private Block block;
	
	public Statement(Expression expression, Block block) {
		this.expression = expression;
		this.block = block;
	}

	public Expression getExpression() {
		return expression;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
		
	}
}
