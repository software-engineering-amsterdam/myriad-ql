package ast.expression;

import ast.Visitor;
import ast.atom.Atom;
import semantic.Environment;
import value.Value;

public class AddExpression extends BinaryExpression {

	public AddExpression(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}

	@Override
	public Atom evaluate() {

//		System.out.println("getLhs: " + getLhs().getNumber());
//		System.out.println("getRhs: " + getRhs().getNumber());
//		System.out.println("getLhs().add(getRhs()): " + getLhs().add(getRhs()).getNumber());
		return getLhs().evaluate().add(getRhs().evaluate());
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


