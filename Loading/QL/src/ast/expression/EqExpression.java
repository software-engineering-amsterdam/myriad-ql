package ast.expression;

import ast.Visitor;

public class EqExpression extends BinaryExpression {
	@Override
	public Boolean evaluate3() {
		if (getLhs() == null || getRhs() == null) {
			// throw exception!
		}

		return getLhs().equals(getRhs());
	}
	@Override
	public void accept(Visitor v) {
		v.visit(this);		
	}
	
}
