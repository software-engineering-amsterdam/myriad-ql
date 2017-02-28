package semantic;


import ast.Visitor;
import ast.atom.Atom;
import ast.expression.BinaryExpression;
import ast.expression.Expression;
import ast.expression.IdExpression;
import ast.expression.UnaryExpression;

// Checks unreferenced variables
// Checks whether condition returns a boolean
// operands of invalid type to operators
public class ExpressionVisitor extends Visitor {
	
	private Environment environment;
	
	public ExpressionVisitor(Environment environment) {
		this.environment = environment;
	}

	public Environment getEnvironment() {
		return environment;
	}

	@Override
	public void visit(BinaryExpression binaryExpression) {
		
		Atom result = binaryExpression.evaluate(environment) ;
		check(result);
		
		System.out.println("Eval: " + result.getValue());
	}
	
	@Override
	public void visit(UnaryExpression unaryExpression) {
		
		Atom result = unaryExpression.evaluate(environment);
		check(result);
		
		System.out.println("Eval: " + result);
	}
	
	@Override 
	public void visit(IdExpression id) {

		System.out.println("IdExpression VISIT");
		
		Atom result = id.evaluate(environment);

		if (!environment.variableExists(id.getName())) {
			throw new RuntimeException("The variable with name " + id.getName() +
					" on line "+ id.getLine() + " is not defined");
		}

		System.out.println("Eval: " + result.getValue());
	}
	
	// TODO do we want to add the throw after this function
	private void check(Atom result) {
		if (result == null) {
			throw new RuntimeException("The expression on line "+ result.getLine() + " cannot be evaluated");
		}
		else if (result.getType() != "boolean") {
			throw new RuntimeException("The condition on line " + result.getLine() + " does not return a boolean");
			// TODO implement line numbers
		}
		
	}
	
}
