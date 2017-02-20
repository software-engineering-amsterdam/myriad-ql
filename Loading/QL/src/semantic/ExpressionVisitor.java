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
		
		Atom result = binaryExpression.evaluate() ;	
		check(result);
		
		System.out.println("Eval: " + result.getValue());
	}
	
	@Override
	public void visit(UnaryExpression unaryExpression) {
		
		Atom result = unaryExpression.evaluate();
		check(result);
		
		System.out.println("Eval: " + result);
	}
	
	@Override 
	public void visit(IdExpression id) {
		
		Atom result = id.evaluate();
		if (result.getType() != "string") { 
			throw new RuntimeException("Expected a id with a variablename, but got type " +
						result.getType());
		}
		
		if (!environment.variableExists(result.getString())) {
			throw new RuntimeException("The variable with name " + result.getString() +
					" on line ... is not defined");
		}

		System.out.println("Eval: " + result);
	}
	
	// TODO do we want to add the throw after this function
	private void check(Atom result) {
		if (result == null) {
			throw new RuntimeException("The expression on line .. cannot be evaluated");
		}
		else if (result.getType() != "boolean") {
			throw new RuntimeException("The condition on line ... does not return a boolean");	
			// TODO implement line numbers
		}
		
	}
	
}
