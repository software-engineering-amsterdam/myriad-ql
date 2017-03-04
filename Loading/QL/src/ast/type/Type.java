package ast.type;

import ast.Node;
import ast.TypeVisitor;
import ui.field.Field;
import value.Value;

public abstract class Type extends Node {
	
	final private String type;
	
	public Type(String type, int line) {
		super(line);
		this.type = type;
	}
	
	// TODO remove
	public String getType() {
		return type;
	}
	
	public abstract Field getField(String name);

	public abstract void accept(TypeVisitor v);

}
