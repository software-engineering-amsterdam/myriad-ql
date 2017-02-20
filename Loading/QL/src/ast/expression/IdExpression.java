package ast.expression;

import ast.Visitor;
import ast.atom.Atom;
import ast.atom.BoolAtom;
import ast.atom.StringAtom;

public class IdExpression extends Expression {

	private String name;
	
	public IdExpression(String name) {
		this.name = name;
	}
	
	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Atom evaluate() {
		return new StringAtom(name); // TODO actually return unknown variable : not a string
		// String Atom
	}
	
}
