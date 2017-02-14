package ast.expression;

import ast.Node;
import ast.Visitor;

public abstract class Expression implements Node {

	public abstract Number evaluate();
	
	@Override
	public abstract void accept(Visitor v);
	
}
