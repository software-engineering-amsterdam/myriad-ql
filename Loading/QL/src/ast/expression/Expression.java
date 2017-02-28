package ast.expression;

import ast.Node;
import ast.atom.Atom;
import value.Value;

// TODO remove expression from name
public abstract class Expression extends Node {
	
	public abstract Atom evaluate();
	
	// TODO remove
	public abstract Atom evaluate(Value test);
	
}
