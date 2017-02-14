package ast.atom;

import ast.Visitor;

public class StringAtom extends Atom {
    private String str;

    public StringAtom(String str) {
        this.str = str;
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
	public BoolAtom and(Atom other) {
		return null;
	}

	@Override
	public Atom div(Atom other) {
		return null;
	}

	@Override
	public Integer getNumber() {
		return null;
	}
	
	@Override
    public Boolean getValue() {        
    	return null;
    }

	@Override
	public String getString() {
		return str;
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
		return null;
	}

	@Override
	public BoolAtom eq(Atom other) {
		return new BoolAtom(str == other.getString());
	}

	@Override
	public BoolAtom greaterEq(Atom other) {
		return null; // TODO do we want to implement this for strings?
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
		return new BoolAtom(str != other.getString());
	}

	@Override
	public BoolAtom or(Atom other) {
		return null;
	}

	@Override
	public Atom sub(Atom other) {
		return null;
	}
}