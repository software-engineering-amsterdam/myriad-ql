package ast.atom;

import ast.ExpressionVisitor;
import ast.expression.Expression;

public abstract class Atom extends Expression {
	
	public Atom(int line) {
		super(line);
	}

	public abstract <T> T accept(ExpressionVisitor<T> v);
}
