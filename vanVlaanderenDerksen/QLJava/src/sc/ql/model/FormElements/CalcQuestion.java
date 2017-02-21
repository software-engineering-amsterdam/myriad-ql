package sc.ql.model.FormElements;

import sc.ql.model.ID;
import sc.ql.model.Expressions.Expression;
import sc.ql.model.Atom;

public class CalcQuestion extends Question {
	private final Expression expression;
	
	public CalcQuestion(String question, ID id, Atom type, Expression expression) {
		super(question, id, type);
		this.expression = expression;
	}
	
	public Expression getExpression() {
		return this.expression;
	}
}