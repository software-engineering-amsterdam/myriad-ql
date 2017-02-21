package ast.expression;

import ast.Visitor;
import ast.atom.Atom;

public class DivExpression extends BinaryExpression {

	@Override
	public Atom evaluate() {
		return getLhs().div(getRhs());
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
