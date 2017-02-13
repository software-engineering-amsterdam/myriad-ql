package ast;

public class Statement extends Node {
	
	private boolean evaluate; // TODO change name
	private Block block;
	
	public Statement(boolean evaluate, Block block) {
		this.evaluate = evaluate;
		this.block = block;
	}
	
}
