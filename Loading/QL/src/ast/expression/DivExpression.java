package ast.expression;

import ast.Visitor;
import ast.atom.Atom;

public class DivExpression extends BinaryExpression {

	@Override
	public Atom evaluate() {

		System.out.println("getLhs: " + getLhs().getNumber());
		System.out.println("getRhs: " + getRhs().getNumber());
		System.out.println("getLhs().div(getRhs()): " + getLhs().div(getRhs()).getNumber());
		return getLhs().div(getRhs());
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
