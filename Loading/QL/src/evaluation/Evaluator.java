//package evaluation;
//
//import ast.Visitor;
//import ast.atom.Atom;
//import ast.atom.BoolAtom;
//import ast.atom.IntegerAtom;
//import ast.atom.StringAtom;
//import ast.expression.BinaryExpression;
//import ast.expression.Expression;
//import ast.expression.IdExpression;
//import ast.expression.UnaryExpression;
//import semantic.ExpressionVisitor;
//import value.Value;
//
//public class Evaluator extends Visitor {
//
//	private Environment environment;
//
//	public void evaluate(Expression expression, Environment environment) {
//		this.environment = environment;
//
//		expression.accept(new Evaluator());
//	}
//
//
////	@Override
////	public Value visit(BinaryExpression binaryExpression) {
////
////		Atom result = binaryExpression.evaluate() ;
////		check(result);
////
////		System.out.println("Eval: " + result.getValue());
////	}
////
////	@Override
////	public Value visit(UnaryExpression unaryExpression) {
////
////		Atom result = unaryExpression.evaluate();
////		check(result);
////
////		System.out.println("Eval: " + result);
////	}
//
//	@Override
//	public void visit(IdExpression id) {
//		Value answer = environment.getAnswer(id.getName());
//		System.out.println("VISIT IDEXPR, ANSWER: " + answer);
//
////		return answer;
//	}
//
//
//	// TODO do we want to add the throw after this function
//	private void check(Atom result) {
//		if (result == null) {
//			throw new RuntimeException("The expression on line .. cannot be evaluated");
//		}
//		else if (result.getType() != "boolean") {
//			throw new RuntimeException("The condition on line ... does not return a boolean");
//			// TODO implement line numbers
//		}
//
//	}
//
//
//
//}
//
//
