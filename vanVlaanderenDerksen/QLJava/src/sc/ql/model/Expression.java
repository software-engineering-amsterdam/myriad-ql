package sc.ql.model;

import sc.ql.model.Node;
import sc.ql.model.visitors.ExpressionVisitor;

public abstract class Expression extends Node {
	public abstract <T> T accept(ExpressionVisitor<T> visitor);
}
