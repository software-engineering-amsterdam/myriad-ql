package QL.ast.atom;

import QL.ast.ExpressionVisitor;
import QL.ast.expression.Expression;

public abstract class Atom extends Expression {
	
	public Atom(int line) {
		super(line);
	}

	public abstract <T> T accept(ExpressionVisitor<T> v);
}
