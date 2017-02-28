package ast.expression;

import ast.Visitor;
import ast.atom.Atom;
import semantic.Environment;

public class GEqExpression extends BinaryExpression {

	public GEqExpression(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public Atom evaluate() {
		return getLhs().evaluate().greaterEq(getRhs().evaluate());
	}

	@Override
	public Atom evaluate(Environment env) {
		return null;
	}
}
