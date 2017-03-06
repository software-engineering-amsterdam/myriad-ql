package sc.ql.model.atoms;

import java.math.BigDecimal;

import sc.ql.model.NodeVisitor;

public class AtomMoney extends Atom {
	private final BigDecimal amount;
	
	public AtomMoney(BigDecimal amount) {
		this.amount = amount;
	}
	
	public BigDecimal getAmount() {
		return this.amount;
	}
	
	@Override
	public <E> E accept(NodeVisitor<E> visitor) throws Exception {
		return visitor.visit(this);
	}
}
