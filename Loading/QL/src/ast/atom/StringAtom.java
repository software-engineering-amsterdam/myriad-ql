package ast.atom;

import ast.Visitor;

public class StringAtom extends Atom {
    private String value;

    public StringAtom(String value) {
        this.value = value;
    }
    
	@Override
	public void accept(Visitor v) {
		v.visit(this);		
	}

	@Override
	public Atom add(Atom other) {
		return null;
	}

	@Override
	public Atom and(Atom other) {
		return null;
	}

	@Override
	public Atom div(Atom other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Number getNumber() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
    public Boolean getValue() {        
    	return null;
    }

	@Override
	public String getString() {
		return value;
	}

	@Override
	public Atom plus() {
		return null;
	}

	@Override
	public Atom min() {
		return null;
	}

	@Override
	public Atom not() {
		return null;
	}
}