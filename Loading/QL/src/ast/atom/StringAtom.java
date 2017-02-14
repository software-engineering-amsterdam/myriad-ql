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
	public Number add() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean and(Atom other) {
		return null;
	}

	@Override
	public Number div() {
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
}