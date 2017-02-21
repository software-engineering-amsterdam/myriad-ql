package ast;

public class Form implements Node {
	
	private final String id;
	private Block block;
	private int line;

	public String getId() {
		return id;
	}
	
	public Block getBlock() {
		return block;
	}

	public int getLine() {
		return line;
	}

	public Form(String id, Block block, int line) {
		this.id = id;
		this.block = block;
		this.line = line;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
	
}
