package ast.expression;

import ast.Node;
import ast.Visitor;
import ast.atom.Atom;
import value.Value;

public abstract class Expression implements Node {
	
	public abstract Atom evaluate();

	public abstract Atom evaluate(Value test);
	
	@Override
	public abstract void accept(Visitor v);
	
}
