package QL.ast.expression;

import QL.ast.ExpressionVisitor;

public class LExpression extends BinaryExpression {

	public LExpression(Expression lhs, Expression rhs, int line) {
		super(lhs, rhs, line);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}

//	@Override
//	public Atom evaluate() {
//		// TODO Auto-generated method stub
//		return getLhs().evaluate().less(getRhs().evaluate());
//	}
}
