package qls.ast;

import QL.ast.Node;

import java.util.List;

public class Section extends Node {
	
	private final String name;
	private final List<Question> questions;
	private final List<DefaultWidget> defaultWidgets;

	public Section(String name, List<Question> questions,  List<DefaultWidget> defaultWidgets, int line) {
		super(line);
		this.name = name;
		this.questions = questions;
		this.defaultWidgets = defaultWidgets;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Question> getQuestions() {
		return questions;
	}

	public List<DefaultWidget> getDefaultWidgets() {
		return defaultWidgets;
	}
	
	
	public void accept(StylesheetVisitor v) {
		v.visit(this);
	}

}
