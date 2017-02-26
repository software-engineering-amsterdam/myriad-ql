package ast.expression;

import ast.Visitor;
import ast.atom.Atom;
import value.Value;

public class GEqExpression extends BinaryExpression {
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public Atom evaluate() {
		return getLhs().greaterEq(getRhs());
	}

	@Override
	public Atom evaluate(Value test) {
		return null;
	}
}
