package ast.expression;

import ast.Visitor;
import ast.atom.Atom;
import semantic.Environment;

public class SubExpression extends BinaryExpression {

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public Atom evaluate() {
		System.out.println("getLhs: " + getLhs().evaluate().getNumber());
		System.out.println("getRhs: " + getRhs().evaluate().getNumber());
//		System.out.println("getLhs().sub(getRhs()): " + getLhs().sub(getRhs()).getNumber());
		return getLhs().evaluate().sub(getRhs().evaluate());
	}

    @Override
	public Atom evaluate(Environment env) {
		return null;
	}
}
