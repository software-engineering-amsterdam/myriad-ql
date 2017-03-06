package ast;

import ast.atom.Atom;
import ast.expression.Expression;
import ast.type.Type;

public class ComputedQuestion extends Question {

	private Expression computedQuestion;

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
