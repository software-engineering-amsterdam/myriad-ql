package QL.ast.expression;

import QL.ast.ExpressionVisitor;

public class AndExpression extends BinaryExpression {

	public AndExpression(Expression lhs, Expression rhs, int line) {
		super(lhs, rhs, line);
	}

//	@Override
//	public Atom evaluate() {
//		return getLhs().evaluate().and(getRhs().evaluate());
//	}
//
//    @Override
//    public Atom evaluate(Environment env) {
//        return null;
//    }

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}


}
