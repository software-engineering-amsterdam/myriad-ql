package QL.ast.expression;

public abstract class UnaryExpr extends Expression {

    private Expression lhs;

	public UnaryExpr(Expression lhs, int line) {
		super(line);
		this.lhs = lhs;
	}

	public Expression getLhs() {
		return lhs;
	}
}
