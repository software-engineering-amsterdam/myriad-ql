package ast.atom;

import ast.Visitor;

public class IntegerAtom extends Atom {
		
	private final Integer number;
	
    public IntegerAtom(Integer number) {
        this.number = number;
    }

	@Override
	public Atom add(Atom other) {
		
		if (other.getNumber() == null) {
			return null;
		}
		
		return new IntegerAtom(number + other.getNumber());
	}

	@Override
	public BoolAtom and(Atom other) {
		return null;
	}

	@Override
	public Atom div(Atom other) {
		if (other.getNumber() == 0 || other.getNumber() == null) {
			return null;
		}
		
		return new IntegerAtom(number / other.getNumber());
	}

	@Override
	public Atom plus() {
		
		return new IntegerAtom(+ number);
	}

	@Override
	public Atom min() {
		return new IntegerAtom(- number);
	}

	@Override
	public BoolAtom not() {
		return null;
	}

	@Override
	public BoolAtom eq(Atom other) {
		return new BoolAtom(number == other.getNumber());
	}

	@Override
	public BoolAtom greaterEq(Atom other) {
		return new BoolAtom(number >= other.getNumber());
	}

	@Override
	public BoolAtom greater(Atom other) {
		return new BoolAtom(number > other.getNumber());
	}

	@Override
	public BoolAtom lessEq(Atom other) {
		return new BoolAtom(number <= other.getNumber());
	}

	@Override
	public BoolAtom less(Atom other) {
		return new BoolAtom(number < other.getNumber());
	}

	@Override
	public Atom mul(Atom other) {
		return new IntegerAtom(number * other.getNumber());
	}

	@Override
	public BoolAtom notEq(Atom other) {
		return new BoolAtom(number != other.getNumber());
	}

	@Override
	public BoolAtom or(Atom other) {
		return null;
	}

	@Override
	public Atom sub(Atom other) {
		
		if (other.getNumber() == null) {
			return null;
		}
		
		return new IntegerAtom(number - other.getNumber());
	}
	
	@Override
	public Boolean getValue() {
		return null;
	}

	@Override
	public String getString() {
		return null;
	}

	@Override
    public Integer getNumber() {
        return this.number;
    }
	
    @Override
    public String getType() {
    	return "integer";
    }
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);		
	}
}