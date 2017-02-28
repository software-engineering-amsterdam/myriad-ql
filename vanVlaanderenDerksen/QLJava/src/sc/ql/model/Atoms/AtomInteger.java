package sc.ql.model.Atoms;

import sc.ql.model.NodeVisitor;

public class AtomInteger extends Atom {
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
