package ast.expression;

import ast.Visitor;

public class DivExpression extends BinaryExpression {

	@Override
	public Number evaluate4() {
		if (getLhs() == null || getRhs() == null) {
			// throw exception!
		}

		return getLhs().div(getRhs());
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
