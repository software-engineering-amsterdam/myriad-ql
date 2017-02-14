package ast.expression;

import ast.Visitor;

public class SubExpression extends BinaryExpression {
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
