package ast.expression;

import ast.Visitor;
import ast.atom.Atom;

public class BinaryExpression extends Expression {

	private Atom lhs;
	private Atom rhs;
	// protected boolean eval;
	
	// TODO is this preferred over a constructor with lhs and rhs?
	public BinaryExpression() {
	}
	
	public BinaryExpression setElements(Atom lhs, Atom rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
		return this;
	}
	
	public Atom getLhs() {
		return lhs;
	}
	
	public Atom getRhs() {
		return rhs;
	}
	
@Override 
public void accept(Visitor v) {
		v.visit(this);
	
	}
	
//	public boolean isEval() {
//		return eval;
//	}

}
