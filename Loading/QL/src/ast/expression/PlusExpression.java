package ast.expression;

import ast.Visitor;
import ast.atom.Atom;
import semantic.Environment;

public class PlusExpression extends UnaryExpression {

	public PlusExpression(Expression lhs, int line) {
		super(lhs, line);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
		
	}

	@Override
	public Atom evaluate() {
		return getLhs().evaluate().plus();
	}

	@Override
	public Atom evaluate(Environment env) {
		return null;
	}

}
