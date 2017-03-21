package QL.ast.type;

import QL.ast.TypeVisitor;

public class StringType extends Type {

	public StringType(int line) {
		super("string", line);
	}
	
	@Override
	public <T> T accept(TypeVisitor<T> v) {
		return v.visit(this);
	}


}
