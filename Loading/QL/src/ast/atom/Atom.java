package ast.atom;

import ast.Visitor;
import ast.expression.Expression;

public class Atom extends Expression {

	@Override
	public void accept(Visitor v) {
		v.visit(this);
		
	}
	
	
}
