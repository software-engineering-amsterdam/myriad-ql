package ast.expression;

import ast.Visitor;

public class AddExpression extends BinaryExpression {
	
	public Number evaluate() {
		
		if (getLhs().getNumber() == null || getRhs().getNumber() == null) {
			// throw exception!
		} 
		
		return getLhs().getNumber().doubleValue() + getRhs().getNumber().doubleValue();
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
