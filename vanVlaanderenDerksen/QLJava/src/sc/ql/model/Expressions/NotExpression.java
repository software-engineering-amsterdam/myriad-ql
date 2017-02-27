package sc.ql.model.expressions;

public class NotExpression extends Expression {
	private final Expression expression;
	
	public NotExpression(Expression expression, Integer line_number) {
		super(line_number);
		this.expression = expression;
	}
	
	public Expression getExpression() {
		return this.expression;
	}
}
