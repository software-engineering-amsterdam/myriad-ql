package sc.ql.model.Atoms;

import sc.ql.model.NodeVisitor;

public class AtomId extends Atom {
	private final String identifier;
	
	public AtomId(String identifier) {
		this.identifier = identifier;
	}
	
	public String getValue() {
		return this.identifier;
	}
	
	@Override
	public <E> E accept(NodeVisitor<E> visitor) throws Exception {
		return visitor.visit(this);
	}
}
