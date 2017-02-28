package semantic;
import ast.Question;
import ast.Visitor;
import ast.atom.Atom;
import ast.atom.BoolAtom;
import ast.type.Type;


// Checks for duplicated questions
// Duplicate Labels (warning)
// TODO change name - Visitor does not add to the understanding
public class QuestionVisitor extends Visitor {
	
	private Environment environment;
	
	public QuestionVisitor(Environment environment) {
		this.environment = environment;
	}
	
	@Override
	public void visit(Question question) {	
		
		question.getType().accept(this);	
		addVariableType(question.getVariable(), question.getType(), question.getLine());
		addLabel(question.getLabel(), question.getVariable(), question.getLine());
	}

	private void addVariableType(String variable, Type type, int line) {
		if (environment.variableExists(variable)) {
			throw new RuntimeException("The variable: " + variable + " on line " + line +
					" cannot be added, because it is already defined");
		}
		environment.addVariableType(variable, type);
	}

	private void addLabel(String label, String variableName, int line) {
		if (environment.labelExists(label)) {
			// TODO WARNING not throw
			System.out.println("The question \" "  + label
					+ " \"on line " + line + " exists twice in the questionnaire.");
		}
		environment.addLabel(label, variableName);
	}
}
