package ql.ast.type;

import ql.ast.TypeVisitor;

public class UnknownType extends Type {

	public UnknownType(int line) {
		super("unknown", line);
	}

	@Override
	public <T> T accept(TypeVisitor<T> v) {
		return v.visit(this);
		
	}

}
