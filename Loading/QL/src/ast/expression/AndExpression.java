package ast.expression;

import ast.Visitor;
import ast.atom.Atom;

public class AndExpression extends BinaryExpression {
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
