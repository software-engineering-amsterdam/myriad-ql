package QL.ast.expression;

import QL.ast.ExpressionVisitor;

public class NEqExpr extends BinaryExpr {

	public NEqExpr(Expression lhs, Expression rhs, int line) {
		super(lhs, rhs, line);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}
}
