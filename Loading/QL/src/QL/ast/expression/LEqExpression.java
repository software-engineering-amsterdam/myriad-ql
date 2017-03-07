package QL.ast.expression;

import QL.ast.ExpressionVisitor;

public class LEqExpression extends BinaryExpression {

	public LEqExpression(Expression lhs, Expression rhs, int line) {
		super(lhs, rhs, line);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}

//	@Override
//	public Atom evaluate() {
//		return getLhs().evaluate().lessEq(getRhs().evaluate());
//	}


}
