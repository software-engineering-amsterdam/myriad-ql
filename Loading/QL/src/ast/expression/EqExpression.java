package ast.expression;

import ast.ExpressionVisitor;
import ast.atom.Atom;
import ast.type.Type;
import semantic.Environment;

public class EqExpression extends BinaryExpression {

	public EqExpression(Expression lhs, Expression rhs, int line) {
		super(lhs, rhs, line);
	}

//	@Override
//	public Atom evaluate() {
//		System.out.println("EqExpression evaluate()");
//		System.out.println(getLhs());
//		System.out.println(getRhs());
//		return getLhs().evaluate().eq(getRhs().evaluate());
//	}

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}
}
