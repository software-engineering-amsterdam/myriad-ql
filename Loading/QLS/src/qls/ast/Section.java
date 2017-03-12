package qls.ast;

import java.util.List;

import QL.ast.Node;

public class Section extends Node {
	
	private final String name;
	private final List<Question> questions;

	public Section(String name, List<Question> questions, int line) {
		super(line);
		this.name = name;
		this.questions = questions;
	}

}
