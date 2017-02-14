package ast.expression;

import ast.Visitor;
import ast.atom.Atom;

public class AndExpression extends BoolExpression {
	
	
	@Override
	public boolean evaluate() {
		return getLhs().evaluate() && getRhs().evaluate();
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}


}
