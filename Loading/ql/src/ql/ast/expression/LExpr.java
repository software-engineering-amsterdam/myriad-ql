package ql.ast.expression;

import ql.ast.ExpressionVisitor;

public class LExpr extends BinaryExpr {

	public LExpr(Expression lhs, Expression rhs, int line) {
		super(lhs, rhs, line);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}
}
