import ast.Question;
import ast.Visitor;

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
		
		environment.addAnswer(question.getLabel(), "answer"); // TODO implement answer
	}
	
}
