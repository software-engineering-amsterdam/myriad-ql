package ast.atom;

import java.lang.Boolean;

import ast.Visitor;

public class BoolAtom extends Atom {
    private Boolean value;

    public BoolAtom(Boolean value) {
        this.value = value;
    }

    public Number getValue() {
        return null;
    }
    
	@Override
	public void accept(Visitor v) {
		v.visit(this);		
	}
}