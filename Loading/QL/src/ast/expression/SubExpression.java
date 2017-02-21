package ast.expression;

import ast.Visitor;
import ast.atom.Atom;

public class SubExpression extends BinaryExpression {

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public Atom evaluate() {
		System.out.println("getLhs: " + getLhs().getNumber());
		System.out.println("getRhs: " + getRhs().getNumber());
		System.out.println("getLhs().sub(getRhs()): " + getLhs().sub(getRhs()).getNumber());
		return getLhs().sub(getRhs());
	}
}
