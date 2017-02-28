package ast.expression;

import ast.Visitor;
import ast.atom.Atom;
import semantic.Environment;

public class OrExpression extends BinaryExpression {

	public OrExpression(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public Atom evaluate() {
		return getLhs().evaluate().or(getRhs().evaluate());
	}

	@Override
	public Atom evaluate(Environment env) {
		return null;
	}
}
