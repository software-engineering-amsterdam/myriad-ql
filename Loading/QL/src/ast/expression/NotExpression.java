package ast.expression;

import ast.Visitor;
import ast.atom.Atom;
import semantic.Environment;

public class NotExpression extends UnaryExpression {

	public NotExpression(Expression lhs) {
		super(lhs);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public Atom evaluate() {
		return getLhs().evaluate().not();
	}

	@Override
	public Atom evaluate(Environment env) {
		return null;
	}
}
