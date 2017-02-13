package ast;

public interface Node {
	public void accept ( Visitor v );
}
