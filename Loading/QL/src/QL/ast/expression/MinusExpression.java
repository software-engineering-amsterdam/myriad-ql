package QL.ast.expression;

import QL.ast.ExpressionVisitor;

public class MinusExpression extends UnaryExpression {

	public MinusExpression(Expression lhs, int line) {
		super(lhs, line);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}

//	@Override
//	public Atom evaluate() {
//		return getLhs().evaluate().min();
//	}

}
