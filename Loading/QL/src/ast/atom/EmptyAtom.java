package ast.atom;

import ast.ExpressionVisitor;

public class EmptyAtom extends Atom {
    
	public EmptyAtom(int line) {
    	super(line);
    }
	
	@Override 
	public Atom add(Atom other) {
		return this;
	}
	
	@Override
	public BoolAtom eq(Atom other) {
		return new BoolAtom(false, 0);
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}
}
