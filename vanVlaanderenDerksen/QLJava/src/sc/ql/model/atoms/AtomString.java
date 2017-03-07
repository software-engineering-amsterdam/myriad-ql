package sc.ql.model.atoms;

import sc.ql.model.NodeVisitor;
import sc.ql.model.expressions.Expression;

public class AtomString extends Expression {
	private final String value;
	
	public AtomString(String value, Integer line_number) {
		super(line_number);
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
