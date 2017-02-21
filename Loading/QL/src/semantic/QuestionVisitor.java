package semantic;
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
	}

	private void addVariableType(String variable, Type type) {
		if (environment.variableExists(variable)) {
			throw new RuntimeException("The variable: " + variable + " on line ... " +
					" cannot be added, because it is already defined");
		}
		environment.addVariableType(variable, type);
	}

	private void addLabel(String label, String variableName) {
		if (environment.labelExists(label)) {
			// TODO WARNING not throw
			System.out.println("The question \" "  + label
					+ " \"on line ... exists twice in the questionnaire.");
		}
		environment.addLabel(label, variableName);
	}
}
