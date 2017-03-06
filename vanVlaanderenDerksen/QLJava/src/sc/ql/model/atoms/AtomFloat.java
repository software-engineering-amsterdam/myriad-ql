package sc.ql.model.atoms;

import sc.ql.model.NodeVisitor;

public class AtomFloat extends Atom {
	private final Float value;
	
	public AtomFloat(Float value) {
		this.value = value;
	}
	
	public Float getValue() {
		return this.value;
	}
	
	@Override
	public <E> E accept(NodeVisitor<E> visitor) throws Exception {
		return visitor.visit(this);
	}
}
