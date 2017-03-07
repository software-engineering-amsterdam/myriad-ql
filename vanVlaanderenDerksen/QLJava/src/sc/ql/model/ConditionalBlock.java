package sc.ql.model;

import java.util.List;

public class ConditionalBlock implements Node {
	private final Node expression;
	private final List<Node> form_elements;
	private final Integer line_number;
	
	public ConditionalBlock(Node expression, List<Node> form_elements, Integer line_number) {
		this.expression = expression;
		this.form_elements = form_elements;
		this.line_number = line_number;
	}
	
	public Node getExpression() {
		return this.expression;
	}
	
	public List<Node> getFormElements() {
		return this.form_elements;
	}
	
	public Integer getLineNumber() {
		return this.line_number;
	}

	@Override
	public <T> T accept(NodeVisitor<T> visitor) throws Exception {
		return visitor.visit(this);
	}
}