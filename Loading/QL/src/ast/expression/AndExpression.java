package ast.expression;

import ast.Visitor;
import ast.atom.Atom;
import semantic.Environment;

public class AndExpression extends BinaryExpression {
	
	
	@Override
	public Atom evaluate() {
		return getLhs().evaluate().and(getRhs().evaluate());
	}

    @Override
    public Atom evaluate(Environment env) {
        return null;
    }

    @Override
	public void accept(Visitor v) {
		v.visit(this);
	}


}
