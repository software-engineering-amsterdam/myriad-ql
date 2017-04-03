package ql.ast.expression;

import ql.ast.ExpressionVisitor;

public class NotExpr extends UnaryExpr {

	public NotExpr(Expression lhs, int line) {
		super(lhs, line);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}
}
