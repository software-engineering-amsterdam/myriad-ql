package ast.type;

import ast.Node;

public abstract class Type implements Node {
	
	final private String type;
	
	public Type(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
}
