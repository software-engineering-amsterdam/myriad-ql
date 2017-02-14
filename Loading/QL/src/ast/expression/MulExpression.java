package ast.expression;

import ast.Visitor;

public class MulExpression extends BinaryExpression {
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
