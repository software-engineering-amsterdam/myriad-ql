package qls.ast;

import QL.ast.Node;

import java.util.Iterator;
import java.util.List;

public class Section extends Node implements Iterable<Question> {

	private final List<Question> questions;
	private final List<DefaultWidget> defaultWidgets;

	public Section(List<Question> questions,  List<DefaultWidget> defaultWidgets, int line) {
		super(line);
		this.questions = questions;
		this.defaultWidgets = defaultWidgets;
	}

	@Override
	public Iterator<Question> iterator() {
		return questions.iterator();
	}
	

	public List<DefaultWidget> getDefaultWidgets() {
		return defaultWidgets;
	}
	
	
	public void accept(StylesheetVisitor v) {
		v.visit(this);
	}

}
