package ast.expression;

import ast.Node;
import ast.Visitor;
import ast.atom.Atom;
import ast.type.Type;
import semantic.Environment;
import value.Value;

public abstract class Expression extends Node {
	
	public abstract Atom evaluate();

	public abstract Atom evaluate(Environment env);
	
	@Override
	public abstract void accept(Visitor v);
	
}
