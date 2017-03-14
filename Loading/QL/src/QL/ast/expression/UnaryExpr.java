package QL.ast.expression;

public abstract class UnaryExpr extends Expression {

    private final Expression lhs;

	UnaryExpr(Expression lhs, int line) {
		super(line);
		this.lhs = lhs;
	}

	public Expression getLhs() {
		return lhs;
	}
}
