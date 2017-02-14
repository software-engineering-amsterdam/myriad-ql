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
	public Number add(Atom other) {
		// TODO Auto-generated method stub
		return value + other.getNumber().intValue();
	}

	@Override
	public Boolean and(Atom other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Number div(Atom other) {
		// TODO Auto-generated method stub
		return value / other.getNumber().floatValue();
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
}