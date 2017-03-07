package QL.ast.expression;

import QL.ast.ExpressionVisitor;

public class OrExpression extends BinaryExpression {

	public OrExpression(Expression lhs, Expression rhs, int line) {
		super(lhs, rhs, line);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}
//	@Override
//	public Atom evaluate() {
//		return getLhs().evaluate().or(getRhs().evaluate());
//	}
}
