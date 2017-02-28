package ast.expression;

import ast.Node;
import ast.atom.Atom;
import ast.type.Type;
import semantic.Environment;
import value.Value;

// TODO remove expression from name
public abstract class Expression extends Node {
	
	public Expression(int line) {
		super(line);
	}

	public abstract Atom evaluate();

	public abstract Atom evaluate(Environment env);


	
}
