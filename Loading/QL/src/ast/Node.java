package ast;

public abstract class Node {
	
	private int line;
	
	public Node(int line) {
		this.line = line;
	}
	
	public abstract void accept(Visitor v);

	public int getLine() {
		return line;
	}
}
