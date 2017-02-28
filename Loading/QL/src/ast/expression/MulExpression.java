package ast.expression;

import ast.Visitor;
import ast.atom.Atom;
import semantic.Environment;

public class MulExpression extends BinaryExpression {
	
	public MulExpression(Expression lhs, Expression rhs, int line) {
		super(lhs, rhs, line);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public Atom evaluate() {

		System.out.println("getLhs: " + getLhs().evaluate().getNumber());
		System.out.println("getRhs: " + getRhs().evaluate().getNumber());
		System.out.println("getLhs().mul(getRhs()): " + getLhs().evaluate().mul(getRhs().evaluate()).getNumber());
		return getLhs().evaluate().mul(getRhs().evaluate());
	}

	@Override
	public Atom evaluate(Environment env) {
		return null;
	}
}
