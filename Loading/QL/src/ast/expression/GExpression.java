package ast.expression;

import ast.Visitor;
import ast.atom.Atom;
import semantic.Environment;

public class GExpression extends BinaryExpression {

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public Atom evaluate() {
		return getLhs().evaluate().greater(getRhs().evaluate());
	}

	@Override
	public Atom evaluate(Environment env) {
		return null;
	}
}
