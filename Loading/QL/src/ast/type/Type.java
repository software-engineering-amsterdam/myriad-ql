package ast.type;

import ast.Node;
import ast.TypeVisitor;
import ast.atom.Atom;
import ui.field.Field;

public abstract class Type extends Node {
	
	final private String keyWord;
	
	public Type(String keyWord, int line) {
		super(line);
		this.keyWord = keyWord;
	}
	
	public String getKeyWord() {
		return keyWord;
	}
	
	public abstract Field getField(String name);
	
	public abstract Atom getAtom();

	public abstract void accept(TypeVisitor v);

}
