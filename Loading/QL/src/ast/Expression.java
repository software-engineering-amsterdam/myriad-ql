package ast;

public class Expression {

	private boolean eval;

	public Expression(Object lhs, Object rhs) {
		// TODO fix eval check
		System.out.println(lhs);
		System.out.println(rhs);
		this.eval = lhs == rhs;
	}

	public boolean isEval() {
		return eval;
	}

}
