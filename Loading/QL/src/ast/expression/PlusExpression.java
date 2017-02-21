package ast.expression;

import ast.Visitor;
import ast.atom.Atom;

public class PlusExpression extends UnaryExpression {

	@Override
	public void accept(Visitor v) {
		v.visit(this);
		
	}

	@Override
	public Atom evaluate() {
		
		// TODO Auto-generated method stub
		return getLhs().plus();
	}

}
