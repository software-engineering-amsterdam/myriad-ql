package sc.ql.model.expressions;

import sc.ql.model.Node;
import sc.ql.model.NodeVisitor;

public class Expression implements Node {
	private Integer line_number;
	
	public Expression() { }
	
	public Expression(Integer line_number) {
		this.line_number = line_number;
	}
	
	public Integer getLineNumber() {
		return this.line_number;
	}
	
	@Override
	public <T> T accept(NodeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}