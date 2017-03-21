package QL.ast.expression;

import QL.ast.ExpressionVisitor;

public class IdExpr extends Expression {

	private final String name;
	
	public IdExpr(String name, int line) {
		super(line);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}
	
}
