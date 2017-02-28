package ast.expression;

import ast.Visitor;
import ast.atom.Atom;
import semantic.Environment;

public class LExpression extends BinaryExpression {

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public Atom evaluate() {
		// TODO Auto-generated method stub
		return getLhs().evaluate().less(getRhs().evaluate());
	}

	@Override
	public Atom evaluate(Environment env) {
		return null;
	}
}
