package QL.ast;

import QL.ast.expression.Expression;
import QL.ast.type.Type;

public class ComputedQuestion extends Question {

	private final Expression computedQuestion;

	public ComputedQuestion(String variable, String label, Type type, Expression computedQuestion, int line) {
		super(variable, label, type, line);
		this.computedQuestion = computedQuestion;
	}

	public Expression getComputedQuestion() {
		return computedQuestion;
	}

	@Override
	public void accept(FormVisitor v) {
		v.visit(this);
	}
}
