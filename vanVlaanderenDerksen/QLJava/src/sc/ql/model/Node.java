package sc.ql.model;

public interface Node {
	public <T> T accept(NodeVisitor<T> visitor);
}