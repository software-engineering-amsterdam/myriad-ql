package sc.ql.model.atoms;

import sc.ql.model.NodeVisitor;
import sc.ql.model.expressions.Expression;

public class AtomBoolean extends Expression {
	private final Boolean value;
	
	public AtomBoolean(Boolean value, Integer line_number) {
		super(line_number);
		this.value = value;
	}
	
	public Boolean getValue() {
		return this.value;
	}
	
	@Override
	public <T> T accept(NodeVisitor<T> visitor) throws Exception {
		return visitor.visit(this);
	}
}
