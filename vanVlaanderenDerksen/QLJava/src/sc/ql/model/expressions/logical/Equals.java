package sc.ql.model.expressions.logical;

import sc.ql.model.Expression;
import sc.ql.model.expressions.BinaryExpression;
import sc.ql.model.visitors.ExpressionVisitor;

public class Equals extends BinaryExpression {

	public Equals(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
