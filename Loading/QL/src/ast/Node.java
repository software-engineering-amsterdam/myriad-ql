package ast;

public interface Node {
	void accept ( Visitor v );
}
