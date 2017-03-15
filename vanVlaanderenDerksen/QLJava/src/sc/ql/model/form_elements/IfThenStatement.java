package sc.ql.model.form_elements;

import java.util.List;
import sc.ql.model.Expression;
import sc.ql.model.FormElement;
import sc.ql.model.visitors.FormElementVisitor;

public class IfThenStatement extends FormElement {
	private final Expression condition;
	private final List<FormElement> thenBody;
	
	public IfThenStatement(Expression condition, List<FormElement> thenBody) {
		this.condition = condition;
		this.thenBody = thenBody;
	}
	
	public Expression getCondition() {
		return this.condition;
	}
	
	public List<FormElement> getThenBody() {
		return this.thenBody;
	}

	@Override
	public <T> T accept(FormElementVisitor<T> visitor) {
		return visitor.visit(this);
	}
}