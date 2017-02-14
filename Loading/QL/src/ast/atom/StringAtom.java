package ast.atom;

import ast.Visitor;

public class StringAtom extends Atom {
    private String value;

    public StringAtom(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
    
	@Override
	public void accept(Visitor v) {
		v.visit(this);		
	}
}