package QL.ast.type;

import QL.ast.TypeVisitor;

public class BooleanType extends Type {
	
	public BooleanType(int line) {
		super("boolean", line);
	}

	@Override
	public <T> T accept(TypeVisitor<T> v) {
		return v.visit(this);	
	}

}
