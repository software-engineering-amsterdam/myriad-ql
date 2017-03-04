package ast.atom;

import ast.ExpressionVisitor;

public class EmptyAtom extends Atom {
    
	// TODO make false dependent on the default value for booleans?
	
	
	
	public EmptyAtom(int line) {
    	super(line);
    }
	
	@Override
	public Atom add(Atom other) { return new EmptyAtom(getLine()); }
	public Atom sub(Atom other) { return new EmptyAtom(getLine()); }
	public Atom mul(Atom other) { return new EmptyAtom(getLine()); }
	public Atom div(Atom other) { return new EmptyAtom(getLine()); }

	public BoolAtom and(Atom other) { return new BoolAtom(false, getLine()); }
	public BoolAtom or(Atom other) { return new BoolAtom(false, getLine()); }
	public BoolAtom eq(Atom other) { return new BoolAtom(false, getLine()); }
	public BoolAtom notEq(Atom other) { return new BoolAtom(false, getLine()); }
	public BoolAtom greaterEq(Atom other) { return new BoolAtom(false, getLine()); }
	public BoolAtom greater(Atom other) { return new BoolAtom(false, getLine()); }
	public BoolAtom lessEq(Atom other) { return new BoolAtom(false, getLine()); }
	public BoolAtom less(Atom other) { return new BoolAtom(false, getLine()); }
	
	// Unary Operators
	public Atom plus() { return new EmptyAtom(getLine()); }
	public Atom min() { return new EmptyAtom(getLine()); }
	public BoolAtom not() { return new BoolAtom(false, getLine()); }
	

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}
}
