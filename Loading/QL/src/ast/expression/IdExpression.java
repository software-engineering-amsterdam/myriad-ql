package ast.expression;

import ast.ExpressionVisitor;
import ast.atom.Atom;
import ast.atom.BoolAtom;
import ast.atom.IntegerAtom;
import ast.atom.StringAtom;
import ast.type.Type;
import semantic.Environment;

public class IdExpression extends Expression {

	private String name;
	
	public IdExpression(String name, int line) {
		super(line);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public Type accept(ExpressionVisitor v) {
		return v.visit(this);
	}
	
//	@Override
//	public Atom evaluate() {
//		System.out.println("idExpression evaluate();");
//		return new BoolAtom(false, getLine());
//	}
}
