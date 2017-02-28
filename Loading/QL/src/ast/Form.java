package ast;

public class Form extends Node {
	
	private final String id;
	private Block block;

	public String getId() {
		return id;
	}
	
	public Block getBlock() {
		return block;
	}
	
	// TODO move line to Node
	public Form(String id, Block block, int line) {
		super(line);
		this.id = id;
		this.block = block;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
	
}
