package sc.ql.model.expressions;

import sc.ql.model.Expression;

public abstract class BinaryExpression extends Expression {
	private final Expression left;
	private final Expression right;
	
	public BinaryExpression(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	
	public Expression getLeft() {
		return this.left;
	}
	
	public Expression getRight() {
		return this.right;
	}
}
