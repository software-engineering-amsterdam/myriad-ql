package ast.atom;

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
	public BoolAtom or(Atom other) {
		return new BoolAtom(value || other.getValue());
	}

	@Override
	public BoolAtom eq(Atom other) {
		return new BoolAtom(value == other.getValue());
	}

	@Override
	public BoolAtom notEq(Atom other) {
		return new BoolAtom(value != other.getValue());
	}

	@Override
	public BoolAtom not() {
		return new BoolAtom(!value);
	}

	@Override
    public Boolean getValue() {
        return this.value;
    }
	
	@Override
	public String getType() {
		return "boolean";
	}

	@Override
	public Atom evaluate(semantic.Environment env) {
		return this;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);		
	}
}