package ast.expression;

import ast.Visitor;
import ast.atom.Atom;
import ast.atom.BoolAtom;
import ast.atom.StringAtom;
import semantic.Environment;
import value.Value;

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
		// TODO we need all operations here?
		// TODO get value of name from Environment
		// TODO cannot be boolatom
		return new BoolAtom(false); // TODO actually return unknown variable : not a string
	}

	@Override
	public Atom evaluate(Value test) {
		System.out.println("TESTING STUFF HERE"+ test.getValue());
		return new BoolAtom((Boolean)  test.getValue());
	}
	
}
