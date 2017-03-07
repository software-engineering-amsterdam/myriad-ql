package QL.ast;

// TODO change to interface??
public abstract class BlockItem extends Node {
	
	public BlockItem(int line) {
		super(line);
	}

	public void accept(FormVisitor v) {
		v.visit(this);
	}
}
