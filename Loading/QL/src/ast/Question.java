package ast;

import ast.type.Type;


public class Question implements Node {

	final private String variable;
	final private String label;
	final private Type type;
	
	public Question(String variable, String label, Type type) {
		this.variable = variable;
		this.label = label;
		this.type = type;
	}
	
	public String getVariable() {
		return variable;
	}

	public String getLabel() {
		return label;
	}

	public Type getType() {
		return type;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
		
	}

}
