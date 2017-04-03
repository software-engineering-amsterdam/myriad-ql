package ql.ast;

import ql.ast.type.Type;

public class Question extends BlockItem {

	final private String name;
	final private String label;
	final private Type type;
	
	public Question(String name, String label, Type type, int line) {
		super(line);
		this.name = name;
		this.label = label;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}

	public String getLabel() {
		return label;
	}

	public Type getType() {
		return type;
	}

	public void accept(FormVisitor v) {
		v.visit(this);		
	}
}
