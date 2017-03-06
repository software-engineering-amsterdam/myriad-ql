package sc.ql.model.Atoms;

import sc.ql.model.NodeVisitor;

public class AtomBoolean extends Atom {
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
