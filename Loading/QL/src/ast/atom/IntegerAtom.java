package ast.atom;

import ast.Visitor;

public class IntegerAtom extends Atom {
		
	private final Integer value;
	
    public IntegerAtom(Integer value) {
        this.value = value;
    }
    
    @Override
    public Number getNumber() {
        return this.value;
    }
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);		
	}
}