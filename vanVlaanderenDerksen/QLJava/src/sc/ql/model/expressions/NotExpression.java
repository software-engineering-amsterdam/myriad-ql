package sc.ql.model.expressions;

import sc.ql.model.Expression;
import sc.ql.model.Node;
import sc.ql.model.visitors.ExpressionVisitor;

public class NotExpression extends Expression {
	private final Node expression;
	
	public NotExpression(Node expression) {
		this.expression = expression;
	}
	
	public Node getExpression() {
		return this.expression;
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
