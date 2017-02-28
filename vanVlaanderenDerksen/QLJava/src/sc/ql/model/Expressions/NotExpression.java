package sc.ql.model.expressions;

import sc.ql.model.Node;
import sc.ql.model.NodeVisitor;

public class NotExpression extends Expression {
	private final Node expression;
	
	public NotExpression(Node expression, Integer line_number) {
		super(line_number);
		this.expression = expression;
	}
	
	public Node getExpression() {
		return this.expression;
	}
	
	@Override
	public <T> T accept(NodeVisitor<T> visitor) throws Exception {
		return visitor.visit(this);
	}
}
