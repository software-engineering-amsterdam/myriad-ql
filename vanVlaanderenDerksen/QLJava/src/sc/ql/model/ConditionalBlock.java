package sc.ql.model;

import java.util.List;

public class ConditionalBlock implements Node {
	private final Node expression;
	private final List<FormElement> form_elements;
	
	public ConditionalBlock(Node expression, List<FormElement> form_elements) {
		this.expression = expression;
		this.form_elements = form_elements;
	}
	
	public Node getExpression() {
		return this.expression;
	}
	
	public List<FormElement> getFormElements() {
		return this.form_elements;
	}

	@Override
	public <T> T accept(NodeVisitor<T> visitor) throws Exception {
		return visitor.visit(this);
	}
}