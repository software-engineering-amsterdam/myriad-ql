package QL.ast;

public abstract class BlockItem extends Node {
	
	BlockItem(int line) {
		super(line);
	}

	public void accept(FormVisitor v) {
		v.visit(this);
	}
}
