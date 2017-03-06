package sc.ql.model.atoms;

import sc.ql.model.Node;
import sc.ql.model.NodeVisitor;

public class AtomInteger implements Node {
	private final Integer value;
	
	public AtomInteger(Integer value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return this.value;
	}
	
	@Override
	public <E> E accept(NodeVisitor<E> visitor) throws Exception {
		return visitor.visit(this);
	}
}
