package ast.expression;

import ast.ExpressionVisitor;
import ast.atom.Atom;
import ast.type.Type;
import semantic.Environment;

public class MulExpression extends BinaryExpression {
	
	public MulExpression(Expression lhs, Expression rhs, int line) {
		super(lhs, rhs, line);
	}

	@Override
	public Type accept(ExpressionVisitor v) {
		return v.visit(this);
	}

//	@Override
//	public Atom evaluate() {
//
//		System.out.println("getLhs: " + getLhs().evaluate().getNumber());
//		System.out.println("getRhs: " + getRhs().evaluate().getNumber());
//		System.out.println("getLhs().mul(getRhs()): " + getLhs().evaluate().mul(getRhs().evaluate()).getNumber());
//		return getLhs().evaluate().mul(getRhs().evaluate());
//	}
}
