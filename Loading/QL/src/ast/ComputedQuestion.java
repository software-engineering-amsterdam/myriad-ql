package ast;

import ast.type.Type;

public class ComputedQuestion extends Question {

	private int computedQuestion;

	public ComputedQuestion(String variable, String label, Type type, int computedQuestion) {
		super(variable, label, type);
		this.computedQuestion = computedQuestion;
	}

	public int getComputedQuestion() {
		return computedQuestion;
	}
}
