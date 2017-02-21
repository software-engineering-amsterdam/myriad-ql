package sc.ql.model.FormElements;

import sc.ql.model.Atom;
import sc.ql.model.ID;
import sc.ql.model.Expressions.Expression;

public class CalcQuestion extends Question {
	private final Expression expression;
	
	public CalcQuestion(String question, ID id, Atom.Type type, Expression expression) {
		super(question, id, type);
		this.expression = expression;
	}
	
	public Expression getExpression() {
		return this.expression;
	}
}