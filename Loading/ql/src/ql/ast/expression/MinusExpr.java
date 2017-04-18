package ql.ast.expression;

import ql.ast.ExpressionVisitor;

public class MinusExpr extends UnaryExpr {

	public MinusExpr(Expression lhs, int line) {
		super(lhs, line);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}
}
