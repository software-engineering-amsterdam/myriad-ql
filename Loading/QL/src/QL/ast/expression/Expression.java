package QL.ast.expression;

import QL.ast.ExpressionVisitor;
import QL.ast.Node;

// TODO remove expression from name
public abstract class Expression extends Node {
	
	public Expression(int line) {
		super(line);
	}

//	public abstract Atom evaluate();
//
//	public abstract Atom evaluate(Environment env);

	public abstract <T> T accept(ExpressionVisitor<T> v);
	
}
