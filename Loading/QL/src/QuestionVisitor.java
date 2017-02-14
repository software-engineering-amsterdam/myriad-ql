import ast.Question;
import ast.Visitor;
import ast.atom.BoolAtom;
import ast.type.Type;

// TODO remove comments
// Checks for double questions
// Saves answers in Environment
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
		environment.addVariableType(question.getVariable(), question.getType());		
		environment.addAnswer(question.getLabel(), new BoolAtom(false)); // TODO implement answers
	}
	
}
