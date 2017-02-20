package ast.atom;

import java.lang.Boolean;

import ast.Visitor;

public class BoolAtom extends Atom {
    private boolean value;

    public BoolAtom(Boolean value) {
    	this.value = value;
    }

	@Override
    public BoolAtom and(Atom other) {
    	return new BoolAtom(value && other.getValue());
    }
	
	@Override
	public Atom div(Atom other) {
		// TODO Auto-generated method stub
		return null;
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
	public BoolAtom not() {
		return new BoolAtom(!value);
	}

	@Override
	public BoolAtom eq(Atom other) {
		return new BoolAtom(value == other.getValue());
	}

	@Override
	public BoolAtom greaterEq(Atom other) {
		return null;
	}

	@Override
	public BoolAtom greater(Atom other) {
		return null;
	}

	@Override
	public BoolAtom lessEq(Atom other) {
		return null;
	}

	@Override
	public BoolAtom less(Atom other) {
		return null;
	}

	@Override
	public Atom mul(Atom other) {
		return null;
	}

	@Override
	public BoolAtom notEq(Atom other) {
		return new BoolAtom(value != other.getValue());
	}

	@Override
	public BoolAtom or(Atom other) {
		return new BoolAtom(value || other.getValue());
	}

	@Override
	public Atom sub(Atom other) {
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
    public Integer getNumber() {
    	return null;
    }
	
	@Override
	public String getType() {
		return "boolean";
	}
    
	@Override
	public void accept(Visitor v) {
		v.visit(this);		
	}
}