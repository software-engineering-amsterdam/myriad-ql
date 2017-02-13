package ast;

public class ComputedQuestion extends Question {

	private int computedQuestion;

	public ComputedQuestion(String variable, String label, String type, int computedQuestion) {
		super(variable, label, type);
		this.computedQuestion = computedQuestion;
	}

	public int getComputedQuestion() {
		return computedQuestion;
	}
}
