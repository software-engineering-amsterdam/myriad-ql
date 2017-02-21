package ui;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ast.Question;
import ast.Statement;
import ast.Visitor;
import ast.type.Type;
import value.Value;

// Or Statement Visitor
public class QuestionnaireVisitor extends Visitor {
	
	private Map<String, Type> activeQuestions; // TODO QQuestion String and type?
	private Map<String, Value> answers;
	
	public QuestionnaireVisitor(Map<String, Value> answers) {
		this.activeQuestions = new HashMap<String, Type>();
		this.answers = answers;
	}
	
	@Override 
	public void visit(Question question) {
        activeQuestions.put(question.getLabel(), question.getType());
	}
	
	@Override
	public void visit(Statement statement) {	
		
		// TODO many functions - functions : can you assume
		// the ATOM is a boolean?
		// System.out.println(statement.getExpression().evaluate());
		// Call the evaluator with answers
		if (answers.size() != 0) {
			System.out.println("answer: ");
			List<Value> valuesList = new ArrayList<Value>(answers.values());
			System.out.println(valuesList.get(0));
		}
		if (statement.getExpression().evaluate().getValue()) {
			statement.getBlock().accept(this);
		} 
	}

	public Map<String, Type> getActiveQuestions() {
		return activeQuestions;
	}	
}
