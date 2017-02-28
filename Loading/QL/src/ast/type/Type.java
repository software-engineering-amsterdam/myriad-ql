package ast.type;

import ast.Node;
import ast.TypeVisitor;

public abstract class Type extends Node {
	
	final private String type;
	
	public Type(String type, int line) {
		super(line);
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	public abstract void accept(TypeVisitor v);

}
