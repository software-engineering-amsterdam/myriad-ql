package ast.expression;

import ast.ExpressionVisitor;
import ast.atom.Atom;
import ast.type.Type;
import semantic.Environment;

public class NotExpression extends UnaryExpression {

	public NotExpression(Expression lhs, int line) {
		super(lhs, line);
	}

	@Override
	public Type accept(ExpressionVisitor v) {
		return v.visit(this);
	}
//	@Override
//	public Atom evaluate() {
//		return getLhs().evaluate().not();
//	}
}
