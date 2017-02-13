package ast.expresssion;

public class EqExpression extends Expression {
	
	public EqExpression(String lhs, String rhs) {
		super(lhs, rhs);
		this.eval = lhs == rhs;
	}
}
