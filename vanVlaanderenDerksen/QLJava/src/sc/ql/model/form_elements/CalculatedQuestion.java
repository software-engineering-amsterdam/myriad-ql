package sc.ql.model.form_elements;

import sc.ql.model.Expression;
import sc.ql.model.expressions.literals.IdLiteral;
import sc.ql.model.types.Type;
import sc.ql.model.visitors.FormElementVisitor;

public class CalculatedQuestion extends Question {
	private final Expression expression;
	
	public CalculatedQuestion(String question, IdLiteral id, Type type, Expression expression) {
		super(question, id, type);
		this.expression = expression;
	}

	public Expression getExpression() {
		return this.expression;
	}
	
	@Override
	public <T> T accept(FormElementVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
