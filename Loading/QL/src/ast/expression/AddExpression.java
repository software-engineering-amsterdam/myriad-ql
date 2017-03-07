package ast.expression;

import ast.ExpressionVisitor;
import ast.atom.Atom;
import ast.type.Type;

public class AddExpression extends BinaryExpression {


	public AddExpression(Expression lhs, Expression rhs, int line) {
		super(lhs, rhs, line);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}
}


