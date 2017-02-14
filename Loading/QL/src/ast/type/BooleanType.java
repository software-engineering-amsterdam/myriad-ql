package ast.type;

import ast.Visitor;

public class BooleanType extends Type {
	
    // TODO why can you not use the constructor of superclass Type directly
	public BooleanType() {
		super("boolean");
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}

}
