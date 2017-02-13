package ast.expresssion;


public abstract class Expression {

	private String lhs;
	private String rhs;
	protected boolean eval;

	public Expression(String lhs, String rhs) {
		// TODO fix eval check
		this.lhs = lhs;
		this.rhs = rhs;
		// this.eval = lhs == rhs;
	}

	public boolean isEval() {
		return eval;
	}

}
