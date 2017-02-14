package ast.expression;

import ast.Visitor;

public class AddExpression extends ArithExpression {
	
	public Number evaluate() {
		
		if (getLhs().getNumber() == null || getRhs().getNumber() == null) {
			// throw exception!
		} 
		
		return getLhs().add(getRhs());
		

		
		// TODO add round function or return int
		return Math.round((getLhs().getNumber().doubleValue() + 
				getRhs().getNumber().doubleValue()) * 100.0 ) / 100.0;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}


