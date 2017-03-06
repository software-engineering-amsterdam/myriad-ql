package sc.ql.model.expressions;

import sc.ql.model.Node;
import sc.ql.model.NodeVisitor;

public class OpExpression extends Expression {
	private final Node left;
	private final Node right;
	private final String operator;
	
	public OpExpression(Node left, Node right, String operator, Integer line_number) {
		super(line_number);
		this.left = left;
		this.right = right;
		this.operator = operator;
	}
	
	public Node getLeft() {
		return this.left;
	}
	
	public Node getRight() {
		return this.right;
	}
	
	public String getOperator() {
		return this.operator;
	}
	
	@Override
	public <T> T accept(NodeVisitor<T> visitor) throws Exception {
		return visitor.visit(this);
	}
}
