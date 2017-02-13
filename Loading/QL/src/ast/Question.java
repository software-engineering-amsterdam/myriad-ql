package ast;

public class Question extends Node {
	
	final private String variable;
	final private String label;
	final private String type;
	private int computedQuestion;
	
	public Question(String variable, String label, String type) {
		this.variable = variable;
		this.label = label;
		this.type = type;
	}
	
	// TODO Could be a computed question with inheritance of Question
	public Question(String variable, String label, String type, int computedQuestion) {
		this.variable = variable;
		this.label = label;
		this.type = type;
		this.computedQuestion = computedQuestion;
	}
	
	public String getVariable() {
		return variable;
	}

	public String getLabel() {
		return label;
	}

	public String getType() {
		return type;
	}
	
	public int getComputedQuestion() {
		return computedQuestion;
	}
}
