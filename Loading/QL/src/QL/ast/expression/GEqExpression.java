package QL.ast.expression;

import QL.ast.ExpressionVisitor;

public class GEqExpression extends BinaryExpression {

	public GEqExpression(Expression lhs, Expression rhs, int line) {
		super(lhs, rhs, line);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}

//	@Override
//	public Atom evaluate() {
//		return getLhs().evaluate().greaterEq(getRhs().evaluate());
//	}
}
