package sc.ql.model.Atoms;

import sc.ql.model.NodeVisitor;

public class AtomString extends Atom {
	private final String value;
	
	public AtomString(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	@Override
	public <E> E accept(NodeVisitor<E> visitor) throws Exception {
		return visitor.visit(this);
	}
}
