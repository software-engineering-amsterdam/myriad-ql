package ast.expression;

import ast.atom.Atom;

public abstract class UnaryExpression extends Expression {
    private Atom lhs;
    // protected boolean eval;

    // TODO is this preferred over a constructor with lhs and rhs?
    public UnaryExpression() {
//		this.lhs = lhs;
//		this.rhs = rhs;
    }

    public void setElements(Atom lhs) {
        this.lhs = lhs;
    }
}
