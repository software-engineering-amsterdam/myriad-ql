package QL.ast.expression;

import QL.ast.ExpressionVisitor;

public class DivExpression extends BinaryExpression {

	public DivExpression(Expression lhs, Expression rhs, int line) {
		super(lhs, rhs, line);
	}

//	@Override
//	public Atom evaluate() {
////		System.out.println("getLhs: " + getLhs().getNumber());
////		System.out.println("getRhs: " + getRhs().getNumber());
////		System.out.println("getLhs().div(getRhs()): " + getLhs().div(getRhs()).getNumber());
//		return getLhs().evaluate().div(getRhs().evaluate());
//	}

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}

}
