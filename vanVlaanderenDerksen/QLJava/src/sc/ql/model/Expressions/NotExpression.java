package sc.ql.model.expressions;

public class NotExpression extends Expression {
	private final Expression expression;
	
	public NotExpression(Expression expression) {
		this.expression = expression;
	}
	
	public Expression getExpression() {
		return this.expression;
	}
}
