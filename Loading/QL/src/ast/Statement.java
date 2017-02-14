package ast;

import ast.expression.Expression;
import ast.expression.EqExpression;

public class Statement implements Node {
	
	// private boolean evaluate; // TODO change name
	private Expression expression; // TODO change name
	private Block block;
	
	public Statement(Expression expression, Block block) {
		this.expression = expression;
		this.block = block;
	}

	public EqExpression getExpression() {
		return (EqExpression) expression;
	}

	public Block getBlock() {
		return block;
	}
	
	// TODO end each class with overrides
	@Override
	public void accept(Visitor v) {
		v.visit(this);
		
	}
}
