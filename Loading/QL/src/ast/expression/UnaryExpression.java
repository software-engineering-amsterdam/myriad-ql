package ast.expression;

import ast.Visitor;

public abstract class UnaryExpression extends Expression {

    private Expression lhs; // TODO rename?
    
    public UnaryExpression(int line) {
    	super(line);
    }
    
    // TODO not pretty to return a unary expression
    public UnaryExpression setElements(Expression lhs) {
        this.lhs = lhs;
        return this;
    }

	public Expression getLhs() {
		return lhs;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
		
	}
}
