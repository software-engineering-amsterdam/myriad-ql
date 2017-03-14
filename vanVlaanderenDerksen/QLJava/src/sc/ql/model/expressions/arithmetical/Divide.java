package sc.ql.model.expressions.arithmetical;

import sc.ql.model.Expression;
import sc.ql.model.expressions.BinaryExpression;
import sc.ql.model.visitors.ExpressionVisitor;

public class Divide extends BinaryExpression {

	public Divide(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
