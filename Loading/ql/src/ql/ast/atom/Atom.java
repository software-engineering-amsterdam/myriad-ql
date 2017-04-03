package ql.ast.atom;

import ql.ast.ExpressionVisitor;
import ql.ast.expression.Expression;

public abstract class Atom extends Expression {
	
	Atom(int line) {
		super(line);
	}

	public abstract <T> T accept(ExpressionVisitor<T> v);
}
