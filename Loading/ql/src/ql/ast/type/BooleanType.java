package ql.ast.type;

import ql.ast.TypeVisitor;

public class BooleanType extends Type {
	
	public BooleanType(int line) {
		super("boolean", line);
	}

	public BooleanType() {
		super("boolean", 0);
	}

	@Override
	public <T> T accept(TypeVisitor<T> v) {
		return v.visit(this);	
	}

}
