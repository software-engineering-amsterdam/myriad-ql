

import ast.Visitor;
import ast.expression.BinaryExpression;
import ast.expression.Expression;
import ast.expression.UnaryExpression;

public class EvalVisitor extends Visitor {
	
	private Environment environment;
	
	public EvalVisitor(Environment environment) {
		this.environment = environment;
	}
	
	@Override
	public void visit(BinaryExpression binaryExpression) {
		
		Expression result = binaryExpression.evaluate() ;	
		if (result == null) {
			throw new RuntimeException("The expression on line ... cannot be evaluated");
		}
		
		System.out.println("Eval: " + result);
	}
	
	@Override
	public void visit(UnaryExpression unaryExpression) {
		
		Expression result = unaryExpression.evaluate();
		if (result == null) {
			throw new RuntimeException("The expression on line .. cannot be evaluated");
		}
		
		System.out.println("Eval: " + result);
	}	
}
