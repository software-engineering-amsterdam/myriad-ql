package sc.ql.model.expressions.literals;

import sc.ql.model.Expression;
import sc.ql.model.visitors.ExpressionVisitor;

public class BooleanLiteral extends Expression {
	private final Boolean value;
	
	public BooleanLiteral(Boolean value) {
		this.value = value;
	}
	
	public Boolean getValue() {
		return this.value;
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
