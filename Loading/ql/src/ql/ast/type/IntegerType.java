package ql.ast.type;

import ql.ast.TypeVisitor;

public class IntegerType extends Type {

	public IntegerType(int line) {
		super("integer", line);
	}

	public IntegerType() {
		super("integer", 0);
	}
	
	@Override
	public <T> T accept(TypeVisitor<T> v) {
		return v.visit(this);
	}

	
}
