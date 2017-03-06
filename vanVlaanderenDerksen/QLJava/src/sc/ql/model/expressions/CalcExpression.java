package sc.ql.model.expressions;

import sc.ql.model.Node;
import sc.ql.model.NodeVisitor;

public class CalcExpression extends OpExpression {

	public CalcExpression(Node left, Node right, String operator, Integer line_number) {
		super(left, right, operator, line_number);
	}
	
	@Override
	public <T> T accept(NodeVisitor<T> visitor) throws Exception {
		return visitor.visit(this);
	}
}
