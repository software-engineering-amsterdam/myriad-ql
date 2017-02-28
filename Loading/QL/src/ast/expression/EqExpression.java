package ast.expression;

import ast.Visitor;
import ast.atom.Atom;
import semantic.Environment;

public class EqExpression extends BinaryExpression {

	public EqExpression(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}

	@Override
	public Atom evaluate() {
		System.out.println("EqExpression evaluate()");
		System.out.println(getLhs());
		System.out.println(getRhs());
		return getLhs().evaluate().eq(getRhs().evaluate());
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);		
	}

	@Override
	public Atom evaluate(Environment env) {
		System.out.println("EqExpression evaluate(env)");
		System.out.println(getLhs());
		System.out.println(getRhs());
		return getLhs().evaluate(env).eq(getRhs().evaluate(env));
	}
	
}
