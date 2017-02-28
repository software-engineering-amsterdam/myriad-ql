package ast.expression;

import ast.ExpressionVisitor;
import ast.atom.Atom;
import ast.type.Type;
import semantic.Environment;

public class MinusExpression extends UnaryExpression {

	public MinusExpression(Expression lhs, int line) {
		super(lhs, line);
	}

	@Override
	public Type accept(ExpressionVisitor v) {
		return v.visit(this);
	}

//	@Override
//	public Atom evaluate() {
//		return getLhs().evaluate().min();
//	}

}
