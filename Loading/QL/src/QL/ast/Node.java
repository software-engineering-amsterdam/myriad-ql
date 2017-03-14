package QL.ast;

public abstract class Node {

	private final int line;

	protected Node(int line) {
		this.line = line;
	}

	public int getLine() {
		return line;
	}
}
