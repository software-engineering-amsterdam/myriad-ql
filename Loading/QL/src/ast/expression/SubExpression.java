package ast.expression;

import ast.Visitor;
import ast.atom.Atom;

public class SubExpression extends BinaryExpression {
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public Atom evaluate() {
		// TODO Auto-generated method stub
		return getLhs().sub(getRhs());
	}
}
