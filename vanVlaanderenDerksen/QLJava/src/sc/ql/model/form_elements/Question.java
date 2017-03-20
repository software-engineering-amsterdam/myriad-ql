package sc.ql.model.form_elements;

import sc.ql.model.FormElement;
import sc.ql.model.expressions.literals.IdLiteral;
import sc.ql.model.types.Type;
import sc.ql.model.visitors.FormElementVisitor;

public class Question extends FormElement {
	private final String label; 
	private final IdLiteral id;
	private final Type type;
	
	public Question(String label, IdLiteral id, Type type) {
		this.label = label;
		this.id = id;
		this.type = type;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public String getId() {
		return this.id.getValue();
	}
	
	public Type getType() {
		return this.type;
	}

	@Override
	public <T> T accept(FormElementVisitor<T> visitor) {
		return visitor.visit(this);
	}
}