package ql.ast.type;

import ql.ast.Node;
import ql.ast.TypeVisitor;

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

	public abstract <T> T accept(TypeVisitor<T> v);

}
