

import ast.Visitor;
import ast.expression.BinaryExpression;

public class EvalVisitor extends Visitor {
	
	private Environment environment;
	
	public EvalVisitor(Environment environment) {
		this.environment = environment;
	}
	
	@Override
	public void visit(BinaryExpression binaryExpression) {
		
		System.out.println("Eval: " + binaryExpression.evaluate());
		System.out.println("Eval2: " + binaryExpression.evaluate2());
		System.out.println("Eval3: " + binaryExpression.evaluate3());
		System.out.println("Eval4: " + binaryExpression.evaluate4());

	}
	
}
