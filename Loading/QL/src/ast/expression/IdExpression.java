package ast.expression;

import ast.Visitor;
import ast.atom.Atom;
import ast.atom.BoolAtom;
import ast.atom.StringAtom;
import semantic.Environment;

public class IdExpression extends Expression {

	private String name;
	
	public IdExpression(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
	
	@Override
	public Atom evaluate() {

		System.out.println("name: " + name);
		// TODO get value of name from Environment
		return new BoolAtom(false); // TODO actually return unknown variable : not a string
	}
	
}
