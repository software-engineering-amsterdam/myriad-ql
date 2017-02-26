package ast.expression;

import ast.Visitor;
import ast.atom.Atom;
import value.Value;

public class LExpression extends BinaryExpression {

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public Atom evaluate() {
		// TODO Auto-generated method stub
		return getLhs().less(getRhs());
	}

	@Override
	public Atom evaluate(Value test) {
		return null;
	}
}
