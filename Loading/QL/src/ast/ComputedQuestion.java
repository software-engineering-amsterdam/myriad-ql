package ast;

import ast.atom.Atom;
import ast.type.Type;

public class ComputedQuestion extends Question {

	private Atom computedQuestion;

	public ComputedQuestion(String variable, String label, Type type, Atom computedQuestion) {
		super(variable, label, type);
		this.computedQuestion = computedQuestion;
	}

	public Atom getComputedQuestion() {
		return computedQuestion;
	}
}
