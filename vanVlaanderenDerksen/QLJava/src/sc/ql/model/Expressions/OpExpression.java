package sc.ql.model.expressions;

public class OpExpression extends Expression {
	private final Expression left;
	private final Expression right;
	private final String operator;
	
	public OpExpression(Expression left, Expression right, String operator) {
		this.left = left;
		this.right = right;
		this.operator = operator;
	}
	
	public Expression getLeft() {
		return this.left;
	}
	
	public Expression getRight() {
		return this.right;
	}
	
	public String getOperator() {
		return this.operator;
	}
}