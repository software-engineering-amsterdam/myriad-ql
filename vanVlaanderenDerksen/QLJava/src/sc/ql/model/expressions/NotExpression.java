package sc.ql.model.expressions;

import sc.ql.model.Expression;
import sc.ql.model.visitors.ExpressionVisitor;

public class NotExpression extends Expression {
	private final Expression expression;
	
	public NotExpression(Expression expression) {
		this.expression = expression;
	}
	
	public Expression getExpression() {
		return this.expression;
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
