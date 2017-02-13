package ast.expression;

import ast.Node;
import ast.Visitor;

public class Expression implements Node {

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}


}
