package ast.atom;

import ast.Visitor;

public class StringAtom extends Atom {
    private String str;

    public StringAtom(String str) {
        this.str = str;
    }

	@Override
	public BoolAtom eq(Atom other) {
		return new BoolAtom(str == other.getString());
	}

	@Override
	public BoolAtom notEq(Atom other) {
		return new BoolAtom(str != other.getString());
	}

	@Override
	public BoolAtom greaterEq(Atom other) {
		return null; // TODO do we want to implement this for strings?
	}
		
	@Override
	public String getString() {
		return str;
	}
	
	@Override
	public String getType() {
		return "string";
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);		
	}

}