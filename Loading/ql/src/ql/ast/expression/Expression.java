package ql.ast.expression;

import ql.ast.ExpressionVisitor;
import ql.ast.Node;


public abstract class Expression extends Node {
	
	protected Expression(int line) {
		super(line);
	}

	public abstract <T> T accept(ExpressionVisitor<T> v);
	
}
