package ast;

public abstract class Node {

	private int line;

	public Node(int line) {
		this.line = line;
	}

	public int getLine() {
		return line;
	}
}
