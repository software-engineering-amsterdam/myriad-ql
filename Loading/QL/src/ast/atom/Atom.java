package ast.atom;

import ast.Visitor;
import ast.expression.Expression;

public abstract class Atom extends Expression {
	
	// TODO can we not implement the default behaviour here?
	public abstract Number add(Atom other);
	public abstract Boolean and(Atom other);
	public abstract Number div(Atom other);
	
	public abstract Number getNumber();
	public abstract Boolean getValue();
	public abstract String getString();

	// TODO do we want to add this?
	@Override
	public abstract void accept(Visitor v);
	
}
