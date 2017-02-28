package ast.expression;

import ast.Visitor;
import ast.atom.Atom;
import semantic.Environment;

public class GExpression extends BinaryExpression {

	public GExpression(Expression lhs, Expression rhs, int line) {
		super(lhs, rhs, line);
	}

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
