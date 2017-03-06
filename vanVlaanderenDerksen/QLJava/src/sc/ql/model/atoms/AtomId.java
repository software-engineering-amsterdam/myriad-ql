package sc.ql.model.atoms;

import sc.ql.model.NodeVisitor;
import sc.ql.model.expressions.Expression;

public class AtomId extends Expression {
	private final String identifier;
	
	public AtomId(String identifier, Integer line_number) {
		super(line_number);
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
