package evaluation;

import ast.Visitor;
import ast.atom.Atom;

public class Evaluator<T> extends Visitor {
	
	private Environment environment;
	
	private void addAnswer(String variable, Atom answer) {
		// TODO check whether the answer has the correct type
//		Type expectedType = semanticEnvironment.hasType(variable);
//		if (answer.getType() != expectedType.getType()) {
//
//			System.out.println("The answer on the question: " + variable
//					+ " should be of type " + expectedType.getType() +
//					" but is of type: " + answer.getType());
//			// TODO print to the screen for the user
//		}
		environment.addAnswer(variable, answer);
	}
	
	
}
