package ast.atom;

import java.lang.Boolean;

import ast.Visitor;

public class BoolAtom extends Atom {
    private boolean value;

    public BoolAtom(Boolean value) {
    	this.value = value;
    }

	@Override
	public Atom add(Atom other) {
		return null;
	}

	@Override
    public Atom and(Atom other) {
    	return new BoolAtom(value && other.getValue());
    }
	
	@Override
	public Atom div(Atom other) {
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
		return new BoolAtom(!value);
	}
}