package ast;

public abstract class Node {
	private int line;

	public abstract void accept(Visitor v);

	public int getLine() {
		return line;
	}

	public void setLine(int i) {
		this.line = i;
	}
}
