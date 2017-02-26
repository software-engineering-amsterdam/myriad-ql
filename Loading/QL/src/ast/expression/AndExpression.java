package ast.expression;

import ast.Visitor;
import ast.atom.Atom;
import value.Value;

public class AndExpression extends BinaryExpression {
	
	
	@Override
	public Atom evaluate() {
		return getLhs().and(getRhs());
	}

    @Override
    public Atom evaluate(Value test) {
        return null;
    }

    @Override
	public void accept(Visitor v) {
		v.visit(this);
	}


}
