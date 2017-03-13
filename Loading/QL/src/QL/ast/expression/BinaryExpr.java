package QL.ast.expression;

public abstract class BinaryExpr extends Expression {

	private Expression lhs;
	private Expression rhs;

	public BinaryExpr(Expression lhs, Expression rhs, int line) {
		super(line);
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public Expression getLhs() {
		return lhs;
	}
	
	public Expression getRhs() {
		return rhs;
	}

}
