package ast.expression;

import ast.Visitor;
import ast.atom.Atom;
import ast.atom.BoolAtom;
import ast.atom.IntegerAtom;
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
		System.out.println("idExpression evaluate();");
		return new BoolAtom(false);
	}

	@Override
	public Atom evaluate(Environment env) {
//		System.out.println("TESTING STUFF HERE"+ test.getValue());
//		return new BoolAtom((Boolean)  test.getValue());
		System.out.println("HAS TYPE: " + env.hasType(name));

		String t = env.hasType(name).getType();
		if (t.equals("integer")) {
			return new IntegerAtom(1);
		}
		if (t.equals("string")) {
			return new StringAtom("string");
		}
		if (t.equals("boolean")) {
			return new BoolAtom(true);
		}

		return null;
	}
	
}
