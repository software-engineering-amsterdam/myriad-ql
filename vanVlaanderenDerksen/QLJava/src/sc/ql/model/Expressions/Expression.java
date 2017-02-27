package sc.ql.model.expressions;

import sc.ql.model.Node;
import sc.ql.model.NodeVisitor;

public class Expression implements Node {
	@Override
	public <T> T accept(NodeVisitor<T> visitor) {
		return null;
	}
}