package sc.ql.model.atoms;

import sc.ql.model.NodeVisitor;
import sc.ql.model.expressions.Expression;

public class AtomInteger extends Expression {
	private final Integer value;
	
	public AtomInteger(Integer value, Integer line_number) {
		super(line_number);
		this.value = value;
	}
	
	public Integer getValue() {
		return this.value;
	}
	
	@Override
	public <T> T accept(NodeVisitor<T> visitor) throws Exception {
		return visitor.visit(this);
	}
}
