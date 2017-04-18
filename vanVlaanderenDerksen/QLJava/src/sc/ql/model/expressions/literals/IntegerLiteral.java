package sc.ql.model.expressions.literals;

import sc.ql.model.Expression;
import sc.ql.model.visitors.ExpressionVisitor;

public class IntegerLiteral extends Expression {
	private final Integer value;
	
	public IntegerLiteral(Integer value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return this.value;
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
