package ast.expression;

import ast.Visitor;
import ast.atom.Atom;

public class MulExpression extends BinaryExpression {
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public Atom evaluate() {

		System.out.println("getLhs: " + getLhs().getNumber());
		System.out.println("getRhs: " + getRhs().getNumber());
		System.out.println("getLhs().mul(getRhs()): " + getLhs().mul(getRhs()).getNumber());
		return getLhs().mul(getRhs());
	}
}
