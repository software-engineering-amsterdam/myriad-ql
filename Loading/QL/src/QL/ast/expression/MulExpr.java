package QL.ast.expression;

import QL.ast.ExpressionVisitor;

public class MulExpr extends BinaryExpr {
	
	public MulExpr(Expression lhs, Expression rhs, int line) {
		super(lhs, rhs, line);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}
}
