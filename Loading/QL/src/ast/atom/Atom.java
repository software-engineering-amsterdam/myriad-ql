package ast.atom;

import ast.Visitor;
import ast.expression.Expression;

public abstract class Atom extends Expression {
	
	// TODO can we not implement the default behaviour here?
	// TODO can BoolAtom also be Boolean?
	public abstract Atom add(Atom other);
	public abstract BoolAtom and(Atom other);
	public abstract Atom div(Atom other);
	public abstract BoolAtom eq(Atom other);
	public abstract BoolAtom greaterEq(Atom other);
	public abstract BoolAtom greater(Atom other);
	public abstract BoolAtom lessEq(Atom other);
	public abstract BoolAtom less(Atom other);
	public abstract Atom mul(Atom other);
	public abstract BoolAtom notEq(Atom other);
	public abstract BoolAtom or(Atom other);
	public abstract Atom sub(Atom other);
	
	public abstract Atom plus();
	public abstract Atom min();
	public abstract BoolAtom not();
	
	public abstract Integer getNumber();
	public abstract Boolean getValue();
	public abstract String getString();

	// TODO do we want to add this?
	@Override
	public abstract void accept(Visitor v);
	
}
