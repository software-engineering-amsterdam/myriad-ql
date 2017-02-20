package evaluation;

import ast.Visitor;
import ast.atom.Atom;
import ast.type.Type;
public class Evaluator extends Visitor {
	
	private semantic.Environment semanticEnv;
	private evaluation.Environment solutionEnv;
	
	public Evaluator(semantic.Environment semanticEnv) {
		this.semanticEnv = semanticEnv;
		this.solutionEnv = new evaluation.Environment();
	}
	
	private void addAnswer(String variable, Atom answer) {
		// TODO check whether the answer has the correct type
		Type expectedType = semanticEnv.hasType(variable);
		if (answer.getType() != expectedType.getType()) {

			System.out.println("The answer on the question: " + variable
					+ " should be of type " + expectedType.getType() +
					" but is of type: " + answer.getType());
			// TODO print to the screen for the user
		}
		solutionEnv.addAnswer(variable, answer);
	}
	
	
}
