package ast.expression;

import ast.Visitor;
import ast.atom.Atom;
import value.Value;

public class OrExpression extends BinaryExpression {

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public Atom evaluate() {
		return getLhs().or(getRhs());
	}

	@Override
	public Atom evaluate(Value test) {
		return null;
	}
}
