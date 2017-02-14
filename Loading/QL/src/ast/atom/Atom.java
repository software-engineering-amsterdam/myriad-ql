package ast.atom;

import ast.Visitor;
import ast.expression.Expression;

public abstract class Atom extends Expression {
	
	// TODO can we not implement the default behaviour here?
	public abstract Atom add(Atom other);
	public abstract Atom and(Atom other);
	public abstract Atom div(Atom other);
	
	public abstract Atom plus();
	public abstract Atom min();
	public abstract Atom not();
	
	public abstract Number getNumber();
	public abstract Boolean getValue();
	public abstract String getString();

	// TODO do we want to add this?
	@Override
	public abstract void accept(Visitor v);
	
}
