

import ast.Visitor;
import ast.expression.BinaryExpression;

public class EvalVisitor extends Visitor {
	
	private Environment environment;
	
	public EvalVisitor(Environment environment) {
		this.environment = environment;
	}
	
	@Override
	public void visit(BinaryExpression binaryExpression) {
		
		System.out.println(binaryExpression.evaluate());

	}
	
}
