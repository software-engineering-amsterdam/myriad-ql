package ast.atom;

import ast.Visitor;

public class IntegerAtom extends Atom {
		
	private final Integer value;
	
    public IntegerAtom(Integer value) {
        this.value = value;
    }
    
	@Override
	public void accept(Visitor v) {
		v.visit(this);		
	}

	@Override
	public Atom add(Atom other) {
		
		if (other.getNumber() == null) {
			return null;
		}
		
		return new IntegerAtom(value + other.getNumber().intValue());
	}

	@Override
	public Atom and(Atom other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Atom div(Atom other) {
		// TODO Auto-generated method stub
		return new IntegerAtom(Math.round(value / other.getNumber().floatValue()));
	}

	@Override
	public Boolean getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getString() {
		return null;
	}

	@Override
    public Number getNumber() {
        return this.value;
    }

	@Override
	public Atom plus() {
		return new IntegerAtom(+ value);
	}

	@Override
	public Atom min() {
		return new IntegerAtom(- value);
	}

	@Override
	public Atom not() {
		return null;
	}
}