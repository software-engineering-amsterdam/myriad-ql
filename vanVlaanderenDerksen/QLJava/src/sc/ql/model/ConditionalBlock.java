package sc.ql.model;

import java.util.List;

import sc.ql.model.Expressions.Expression;

public class ConditionalBlock implements Node {
	private final Expression expression;
	private final List<FormElement> form_elements;
	
	public ConditionalBlock(Expression expression, List<FormElement> form_elements) {
		this.expression = expression;
		this.form_elements = form_elements;
	}
	
	public Expression getExpression() {
		return this.expression;
	}
	
	public List<FormElement> getFormElements() {
		return this.form_elements;
	}
}