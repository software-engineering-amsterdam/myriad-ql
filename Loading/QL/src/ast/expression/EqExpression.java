package ast.expression;

import ast.Visitor;
import ast.atom.Atom;

public class EqExpression extends BinaryExpression {

	@Override
	public Atom evaluate() {
		return getLhs().eq(getRhs());
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);		
	}
	
}
