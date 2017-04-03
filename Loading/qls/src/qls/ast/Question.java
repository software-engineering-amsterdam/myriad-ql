package qls.ast;

import ql.ast.Node;

public class Question extends Node {
	
	final private String name;
	
	public Question(String name, int line) {
		super(line);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void accept(StylesheetVisitor v) {
		v.visit(this);
	}

}
