package ast;

import ast.expression.Expression;

public class Statement extends BlockItem {
	
	private Expression expression; // TODO change name
	private Block block;
	
	public Statement(Expression expression, Block block, int line) {
		super(line);
		this.expression = expression;
		this.block = block;
	}

	public Expression getExpression() {
		return expression;
	}

	public Block getBlock() {
		return block;
	}
	
	// TODO end each class with overrides?

}
