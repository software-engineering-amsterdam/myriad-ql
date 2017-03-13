package qls.ast;

import QL.ast.Node;

public class Question extends Node {
	
	final private String name;
	
	public Question(String name, int line) {
		super(line);
		this.name = name;
	}

}
