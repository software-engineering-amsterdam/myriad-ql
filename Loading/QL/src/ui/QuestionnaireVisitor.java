package ui;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ast.Question;
import ast.Statement;
import ast.Visitor;
import value.Value;

// Or Statement Visitor
public class QuestionnaireVisitor extends Visitor {
	
	private List<String> activeQuestions;
	private Map<String, Value> answers;
	
	public QuestionnaireVisitor(Map<String, Value> answers) {
		this.activeQuestions = new ArrayList<String>();
		this.answers = answers;
	}
	
	@Override 
	public void visit(Question question) {
        activeQuestions.add(question.getLabel());
	}
	
	@Override
	public void visit(Statement statement) {	
		
		// TODO many functions - functions : can you assume the ATOM is a boolean?
		 System.out.println(statement.getExpression().evaluate().getValue());
		// Call the evaluator with answers
		if (answers.size() != 0) {
			System.out.println("answer: ");
			List<Value> valuesList = new ArrayList<Value>(answers.values());
			List<String> keysList = new ArrayList<String>(answers.keySet());
			System.out.println(valuesList.get(0).getValue());
			System.out.println(keysList.get(0));
			System.out.println(statement.getExpression().evaluate().getValue());
		}
		if (statement.getExpression().evaluate().getValue()) {
			statement.getBlock().accept(this);
		} 
	}

	public List<String> getActiveQuestions() {
		return activeQuestions;
	}	
}
