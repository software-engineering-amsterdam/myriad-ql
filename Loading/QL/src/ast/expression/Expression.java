package ast.expression;

import ast.Node;
import ast.Visitor;
import ast.atom.Atom;

public abstract class Expression implements Node {
	
	public abstract Atom evaluate();
	
	@Override
	public abstract void accept(Visitor v);
	
}
