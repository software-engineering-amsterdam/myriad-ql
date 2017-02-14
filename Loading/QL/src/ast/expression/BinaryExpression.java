package ast.expression;

import ast.Visitor;
import ast.atom.Atom;

public class BinaryExpression extends Expression {

	private Atom lhs;
	private Atom rhs;
	// protected boolean eval;
	
	// TODO is this preferred over a constructor with lhs and rhs?
	public BinaryExpression() {
//		this.lhs = lhs;
//		this.rhs = rhs;
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
	
	@Override // TODO or should this be an abstract class without accept here?
	public void accept(Visitor v) {
		v.visit(this);
		
	}
	
//	public boolean isEval() {
//		return eval;
//	}

}
