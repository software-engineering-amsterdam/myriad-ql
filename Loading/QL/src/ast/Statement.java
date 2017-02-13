package ast;

import ast.expresssion.Expression;

public class Statement extends Node {
	
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
}
