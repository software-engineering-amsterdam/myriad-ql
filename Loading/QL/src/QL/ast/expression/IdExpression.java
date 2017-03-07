package QL.ast.expression;

import QL.ast.ExpressionVisitor;

public class IdExpression extends Expression {

	private String name;
	
	public IdExpression(String name, int line) {
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
