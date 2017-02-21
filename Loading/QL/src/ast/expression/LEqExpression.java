package ast.expression;

import ast.Visitor;
import ast.atom.Atom;

public class LEqExpression extends BinaryExpression {

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public Atom evaluate() {
		return getLhs().lessEq(getRhs());
	}
}
