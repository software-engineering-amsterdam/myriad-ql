package ast;

public class Statement {


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
