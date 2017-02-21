package ast.expression;

import ast.Visitor;
import ast.atom.Atom;

public class AddExpression extends BinaryExpression {
	
	public Atom evaluate() {

		System.out.println("getLhs: " + getLhs().getNumber());
		System.out.println("getRhs: " + getRhs().getNumber());
		System.out.println("getLhs().add(getRhs()): " + getLhs().add(getRhs()).getNumber());
		return getLhs().add(getRhs());
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}


