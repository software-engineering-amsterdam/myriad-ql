package ast.type;

import ast.Node;

public abstract class Type extends Node {
	
	final private String type;
	
	public Type(String type, int line) {
		super(line);
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
}
