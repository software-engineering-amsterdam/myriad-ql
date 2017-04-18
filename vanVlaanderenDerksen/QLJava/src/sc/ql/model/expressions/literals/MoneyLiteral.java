package sc.ql.model.expressions.literals;

import java.math.BigDecimal;

import sc.ql.model.Expression;
import sc.ql.model.visitors.ExpressionVisitor;

public class MoneyLiteral extends Expression {
	private final BigDecimal value;
	
	public MoneyLiteral(BigDecimal value) {
		this.value = value;
	}
	
	public BigDecimal getValue() {
		return this.value;
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
