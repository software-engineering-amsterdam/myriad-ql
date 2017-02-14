package ast.expression;

import ast.Visitor;

public class MinusExpression extends UnaryExpression {

	@Override
	public void accept(Visitor v) {
		v.visit(this);
		
	}

}
