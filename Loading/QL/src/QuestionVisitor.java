import ast.Question;
import ast.Visitor;
import ast.atom.Atom;
import ast.atom.BoolAtom;
import ast.type.Type;


// Checks for duplicated questions
// Duplicate Labels (warning)
public class QuestionVisitor extends Visitor {
	
	private Environment environment;
	
	public QuestionVisitor(Environment environment) {
		this.environment = environment;
	}
	
	public Environment getEnvironment() {
		return environment;
	}
	
	@Override
	public void visit(Question question) {	
		
		question.getType().accept(this);	
		addVariableType(question.getVariable(), question.getType());
		addLabel(question.getLabel(), question.getVariable());
		
		// TODO implement answers should this not be part of evaluation?
		addAnswer(question.getVariable(), new BoolAtom(false));
		
	}

	private void addVariableType(String variable, Type type) {
		if (environment.variableType.containsKey(variable)) {
			throw new RuntimeException("The variable: " + variable + " on line ... " +
					" cannot be added, because it is already defined");
		}
		environment.addVariableType(variable, type);
	}

	private void addLabel(String label, String variableName) {
		if (environment.labelVariable.containsKey(label)) {
			// TODO WARNING not throw
			System.out.println("The question \" "  + label
					+ " \"on line ... exists twice in the questionnaire.");
		}
		environment.addLabel(label, variableName);
	}

	private void addAnswer(String variable, Atom answer) {
		// TODO check whether the answer has the correct type
		Type expectedType = environment.variableType.get(variable);
		if (answer.getType() != expectedType.getType()) {

			System.out.println("The answer on the question: " + variable
					+ " should be of type " + expectedType.getType() +
					" but is of type: " + answer.getType());
			// TODO print to the screen for the user
		}
		environment.addAnswer(variable, answer);
	}
	
}
