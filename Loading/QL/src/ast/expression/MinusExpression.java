package ast.expression;

import ast.Visitor;
import ast.atom.Atom;
import value.Value;

public class MinusExpression extends UnaryExpression {

	@Override
	public void accept(Visitor v) {
		v.visit(this);
		
	}

	@Override
	public Atom evaluate() {
		return getLhs().min();
	}

	@Override
	public Atom evaluate(Value test) {
		return null;
	}

}
