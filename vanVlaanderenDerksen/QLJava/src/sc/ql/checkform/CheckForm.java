package sc.ql.checkform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sc.ql.model.form_elements.*;
import sc.ql.model.Form;
import sc.ql.model.Node;
import sc.ql.model.atoms.AtomId;

public class CheckForm {
	public CheckForm(Form form) throws Exception {
		List<Node> form_elements = form.getFormElements();
		List<Question> questions = createQuestionsList(form_elements);
				
		checkQuestions(questions);
		checkConditions(form_elements, questions);
		checkDependencies(form_elements);
	}
	
	private HashMap<String, Question.Type> createIdTypeHashmap(List<Question> questions) {
		HashMap<String, Question.Type> identifier_types = new HashMap<String, Question.Type>();
		
		for (Question question : questions) {
			identifier_types.put(question.getId().getValue(), question.getType());
		}
		
		return identifier_types;
	}
	
	private void checkDependencies(List<Node> form_elements) {
		try {
			DependenciesVisitor dependencies = new DependenciesVisitor();
			for (Node form_element : form_elements) {
				form_element.accept(dependencies);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void checkConditions(List<Node> form_elements, List<Question> questions) {
		try {
			ConditionsVisitor condition_visitor = new ConditionsVisitor(createIdTypeHashmap(questions));
			for (Node form_element : form_elements) {
				form_element.accept(condition_visitor);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void checkQuestions(List<Question> questions) throws Exception {
		HashMap<AtomId, Question> identifier_hm = new HashMap<AtomId, Question>();
		HashMap<String, Question> question_hm = new HashMap<String, Question>();
		
		for (Question question : questions) {
			if (question.getQuestion().isEmpty()) {
				throw new Exception("Undefined question '"+question.getId().getValue()+"' at line: "+question.getLineNumber());
			}
			
			// Check for duplicate identifiers
			AtomId identifier = question.getId();
			if (identifier_hm.containsKey(identifier)) {
				throw new Exception("Question '"+question.getId().getValue()+"' already declared at line "+identifier_hm.get(identifier).getLineNumber()+", duplicate at line "+question.getLineNumber());
			}
			else {
				identifier_hm.put(identifier, question);
			}
			
			// Check for duplicate question labels
			String question_str = question.getQuestion();
			if (question_hm.containsKey(question_str)) {
				String first_element = "'"+question_hm.get(question_str).getId().getValue()+"' (line "+question_hm.get(question_str).getLineNumber()+")";
				String second_element = "'"+question.getId().getValue()+"' (line "+question.getLineNumber()+")";
				throw new Exception("Question "+first_element+" and question "+second_element+" have the same labels.");
			}
			else {
				question_hm.put(question_str, question);
			}
		}
	}
	
	private List<Question> createQuestionsList(List<Node> form_elements) {
		List<Question> questions = new ArrayList<Question>();
		
		for (Node form_element : form_elements) {
			try {
				questions.addAll(form_element.accept(new QuestionsVisitor()));
			} catch (Exception e) {
				e.printStackTrace();
			}
        }

		return questions;
	}
}
