package sc.ql.model.expressions.literals;

import sc.ql.model.Expression;
import sc.ql.model.visitors.ExpressionVisitor;

public class StringLiteral extends Expression {
	private final String value;
	
	public StringLiteral(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
