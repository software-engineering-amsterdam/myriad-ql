package ast.atom;

import java.lang.Boolean;

import ast.Visitor;

public class BoolAtom extends Atom {
    private boolean value;

    public BoolAtom(Boolean value) {
    	this.value = value;
    }

	@Override
	public Number add(Atom other) {
		return null;
	}

	@Override
    public Boolean and(Atom other) {
    	return value && other.getValue();
    }
	
	@Override
	public Number div(Atom other) {
		// TODO Auto-generated method stub
		return null;
	}
    
	@Override
    public Boolean getValue() {
        return this.value;
    }

	@Override
	public String getString() {
		return null;
	}

	@Override
    public Number getNumber() {
    	return null;
    }
    
	@Override
	public void accept(Visitor v) {
		v.visit(this);		
	}
}