package ast.expression;

import ast.Visitor;
import ast.atom.Atom;

public class AndExpression extends BinaryExpression {
	
	
	@Override
	public Atom evaluate() {
		return getLhs().and(getRhs());
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}


}
