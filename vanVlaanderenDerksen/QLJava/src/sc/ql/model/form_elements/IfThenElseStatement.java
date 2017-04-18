package sc.ql.model.form_elements;

import java.util.List;

import sc.ql.model.FormElement;
import sc.ql.model.visitors.FormElementVisitor;
import sc.ql.model.Expression;

public class IfThenElseStatement extends IfThenStatement {
	private final List<FormElement> elseBody;
	
	public IfThenElseStatement(Expression condition, List<FormElement> thenBody, List<FormElement> elseBody) {
		super(condition, thenBody);
		this.elseBody = elseBody;
	}

	public List<FormElement> getElseBody() {
		return this.elseBody;
	}
	
	@Override
	public <T> T accept(FormElementVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
