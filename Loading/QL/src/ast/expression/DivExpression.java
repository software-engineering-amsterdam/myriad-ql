package ast.expression;

import ast.Visitor;

public class DivExpression extends BinaryExpression {
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
