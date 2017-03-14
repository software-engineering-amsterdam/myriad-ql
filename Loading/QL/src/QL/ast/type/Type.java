package QL.ast.type;

import QL.ast.Node;
import QL.ast.TypeVisitor;
import QL.ui.Notifier;
import QL.ui.field.Field;
import QL.value.Value;

public abstract class Type extends Node {
	
	final private String keyWord;
	
	Type(String keyWord, int line) {
		super(line);
		this.keyWord = keyWord;
	}
	
	public String getKeyWord() {
		return keyWord;
	}
	
	public boolean equals(Type other) {
		return keyWord.equals(other.getKeyWord());
	}

	// TODO field and QL.value in here?
	public abstract Field getField(String name, Notifier notifier, Value value);
	
	public abstract Value getValue();

	public abstract void accept(TypeVisitor v);

}
