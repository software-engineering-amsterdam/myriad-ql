package ast.expression;

import ast.Visitor;
import ast.atom.Atom;

public class NotExpression extends UnaryExpression {
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
