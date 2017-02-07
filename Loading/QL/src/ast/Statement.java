package ast;

public class Statement {
	
	private boolean evaluate; // TODO change name
	private Block block;
	
	public Statement(boolean evaluate, Block block) {
		this.evaluate = evaluate;
		this.block = block;
	}
	
}
