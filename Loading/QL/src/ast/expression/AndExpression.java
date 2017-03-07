package ast.expression;

import ast.ExpressionVisitor;
import ast.atom.Atom;
import ast.type.Type;
import semantic.Environment;

public class AndExpression extends BinaryExpression {

	public AndExpression(Expression lhs, Expression rhs, int line) {
		super(lhs, rhs, line);
	}

//	@Override
//	public Atom evaluate() {
//		return getLhs().evaluate().and(getRhs().evaluate());
//	}
//
//    @Override
//    public Atom evaluate(Environment env) {
//        return null;
//    }

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}


}
