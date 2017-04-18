package ql.ast.expression;

import ql.ast.ExpressionVisitor;

public class EqExpr extends BinaryExpr {

	public EqExpr(Expression lhs, Expression rhs, int line) {
		super(lhs, rhs, line);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}
}
