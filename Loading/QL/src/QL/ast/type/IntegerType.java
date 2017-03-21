package QL.ast.type;

import QL.ast.TypeVisitor;

public class IntegerType extends Type {

	public IntegerType(int line) {
		super("integer", line);
	}
	
	@Override
	public <T> T accept(TypeVisitor<T> v) {
		return v.visit(this);
	}

	
}
