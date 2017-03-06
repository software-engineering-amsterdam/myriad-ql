package ast.atom;

import ast.ExpressionVisitor;

public class EmptyAtom extends Atom {
    
	// TODO make false dependent on the default value for booleans?
	public EmptyAtom(int line) {
    	super(line);
    }
	
	@Override
	public Atom add(Atom other) { return new EmptyAtom(getLine()); }
	
	@Override
	public Atom sub(Atom other) { return new EmptyAtom(getLine()); }
	
	@Override
	public Atom mul(Atom other) { return new EmptyAtom(getLine()); }
	
	@Override
	public Atom div(Atom other) { return new EmptyAtom(getLine()); }
	
	@Override
	public BoolAtom and(Atom other) { return new BoolAtom(false, getLine()); }
	
	@Override
	public BoolAtom or(Atom other) { return new BoolAtom(false, getLine()); }
	
	@Override
	public BoolAtom eq(Atom other) { return new BoolAtom(false, getLine()); }
	
	@Override
	public BoolAtom notEq(Atom other) { return new BoolAtom(false, getLine()); }
	
	@Override
	public BoolAtom greaterEq(Atom other) { return new BoolAtom(false, getLine()); }
	
	@Override
	public BoolAtom greater(Atom other) { return new BoolAtom(false, getLine()); }
	
	@Override
	public BoolAtom lessEq(Atom other) { return new BoolAtom(false, getLine()); }
	
	@Override
	public BoolAtom less(Atom other) { return new BoolAtom(false, getLine()); }
	
	// Unary Operators
	@Override
	public Atom plus() { return new EmptyAtom(getLine()); }
	
	@Override
	public Atom min() { return new EmptyAtom(getLine()); }
	
	@Override
	public BoolAtom not() { return new BoolAtom(false, getLine()); }
	

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}
}
