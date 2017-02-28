package ast.expression;

import ast.ExpressionVisitor;
import ast.atom.Atom;
import ast.type.Type;
import semantic.Environment;

public class GExpression extends BinaryExpression {

	public GExpression(Expression lhs, Expression rhs, int line) {
		super(lhs, rhs, line);
	}

	@Override
	public Type accept(ExpressionVisitor v) {
		return v.visit(this);
	}
//
//	@Override
//	public Atom evaluate() {
//		return getLhs().evaluate().greater(getRhs().evaluate());
//	}

}
