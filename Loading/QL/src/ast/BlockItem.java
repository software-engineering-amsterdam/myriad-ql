package ast;

// TODO change to interface??
public abstract class BlockItem extends Node {
	
	public BlockItem(int line) {
		super(line);
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);	
	}
	
}
