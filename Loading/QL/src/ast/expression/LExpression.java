package ast.expression;

import ast.ExpressionVisitor;
import ast.atom.Atom;
import ast.type.Type;
import semantic.Environment;

public class LExpression extends BinaryExpression {

	public LExpression(Expression lhs, Expression rhs, int line) {
		super(lhs, rhs, line);
	}

	@Override
	public Type accept(ExpressionVisitor v) {
		return v.visit(this);
	}

//	@Override
//	public Atom evaluate() {
//		// TODO Auto-generated method stub
//		return getLhs().evaluate().less(getRhs().evaluate());
//	}
}
