package sc.ql.model.atoms;

import sc.ql.model.Node;
import sc.ql.model.NodeVisitor;

public class AtomBoolean implements Node {
	private final Boolean value;
	
	public AtomBoolean(Boolean value) {
		this.value = value;
	}
	
	public Boolean getValue() {
		return this.value;
	}
	
	@Override
	public <E> E accept(NodeVisitor<E> visitor) throws Exception {
		return visitor.visit(this);
	}
}
