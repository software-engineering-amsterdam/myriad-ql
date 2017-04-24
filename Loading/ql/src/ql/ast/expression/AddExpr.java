package ql.ast.expression;

import ql.ast.ExpressionVisitor;

public class AddExpr extends BinaryExpr {


	public AddExpr(Expression lhs, Expression rhs, int line) {
		super(lhs, rhs, line);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}
}


