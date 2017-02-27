package ast.expression;

import ast.Visitor;
import ast.atom.Atom;
import value.Value;

public class PlusExpression extends UnaryExpression {

	@Override
	public void accept(Visitor v) {
		v.visit(this);
		
	}

	@Override
	public Atom evaluate() {
		return getLhs().plus();
	}

	@Override
	public Atom evaluate(Value test) {
		return null;
	}

}
