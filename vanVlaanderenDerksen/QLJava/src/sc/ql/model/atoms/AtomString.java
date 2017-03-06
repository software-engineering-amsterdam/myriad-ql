package sc.ql.model.atoms;

import sc.ql.model.Node;
import sc.ql.model.NodeVisitor;

public class AtomString implements Node {
	private final String value;
	
	public AtomString(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	@Override
	public <T> T accept(NodeVisitor<T> visitor) throws Exception {
		return visitor.visit(this);
	}
}
