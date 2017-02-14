package ast.expression;

import ast.Visitor;

public class PlusExpression extends UnaryExpression {

	@Override
	public void accept(Visitor v) {
		v.visit(this);
		
	}

}
