package ql.ast.type;

import ql.ast.TypeVisitor;

public class StringType extends Type {

	public StringType(int line) {
		super("string", line);
	}

	public StringType() {
		super("string", 0);
	}
	
	@Override
	public <T> T accept(TypeVisitor<T> v) {
		return v.visit(this);
	}


}
