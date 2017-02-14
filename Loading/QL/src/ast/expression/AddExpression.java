package ast.expression;

import ast.Visitor;
import ast.atom.Atom;

public class AddExpression extends BinaryExpression {
	
	public Atom evaluate() {
		
		return getLhs().add(getRhs());
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}


