package sc.ql.model.atoms;

import sc.ql.model.Node;
import sc.ql.model.NodeVisitor;

public class AtomId implements Node {
	private final String identifier;
	
	public AtomId(String identifier) {
		this.identifier = identifier;
	}
	
	public String getValue() {
		return this.identifier;
	}
	
	@Override
	public <T> T accept(NodeVisitor<T> visitor) throws Exception {
		return visitor.visit(this);
	}
}