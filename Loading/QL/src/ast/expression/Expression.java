package ast.expression;

import ast.Node;
import ast.Visitor;

public abstract class Expression implements Node {

	@Override
	public abstract void accept(Visitor v);
	
}
