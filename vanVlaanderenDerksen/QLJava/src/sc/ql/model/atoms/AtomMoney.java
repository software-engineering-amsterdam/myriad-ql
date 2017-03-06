package sc.ql.model.atoms;

import java.math.BigDecimal;

import sc.ql.model.NodeVisitor;
import sc.ql.model.expressions.Expression;

public class AtomMoney extends Expression {
	private final BigDecimal amount;
	
	public AtomMoney(BigDecimal amount, Integer line_number) {
		super(line_number);
		this.amount = amount;
	}
	
	public BigDecimal getAmount() {
		return this.amount;
	}
	
	@Override
	public <T> T accept(NodeVisitor<T> visitor) throws Exception {
		return visitor.visit(this);
	}
}
