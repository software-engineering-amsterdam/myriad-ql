package ast.expression;

import ast.Visitor;
import ast.atom.Atom;

public class UnaryExpression extends Expression {
    private Atom lhs; // TODO rename?
    // protected boolean eval;
    
    // TODO remove empty constructor?
    public UnaryExpression() {
    }
    
    // TODO not pretty to return a unary expression
    public UnaryExpression setElements(Atom lhs) {
        this.lhs = lhs;
        return this;
    }

	public Atom getLhs() {
		return lhs;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
		
	}
}
