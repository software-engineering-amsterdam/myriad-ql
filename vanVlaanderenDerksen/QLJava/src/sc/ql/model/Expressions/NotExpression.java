package sc.ql.model.Expressions;

public class NotExpression extends Expression {
	private final Expression expression;
	
	public NotExpression(Expression expression) {
		this.expression = expression;
	}
	
	public Expression getExpression() {
		return this.expression;
	}
}
