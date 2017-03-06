package sc.ql.model.atoms;

import java.math.BigDecimal;

import sc.ql.model.Node;
import sc.ql.model.NodeVisitor;

public class AtomMoney implements Node {
	private final BigDecimal amount;
	
	public AtomMoney(BigDecimal amount) {
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
