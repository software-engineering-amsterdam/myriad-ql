package sc.ql.checkform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sc.ql.model.form_elements.*;
import sc.ql.model.Atom;
import sc.ql.model.Form;
import sc.ql.model.FormElement;
import sc.ql.model.expressions.Expression;

public class CheckForm {
	public CheckForm(Form form) throws Exception {
		List<FormElement> form_elements = form.getFormElements();
		List<Question> questions = new ArrayList<Question>();
		List<Expression> conditions = new ArrayList<Expression>();
		
		for (FormElement form_element : form_elements) {
			questions.addAll(form_element.accept(new GetFormQuestions()));
			conditions.addAll(form_element.accept(new GetFormConditions()));
        }
		
		checkQuestions(questions);
		checkConditions(conditions);
	}
	
	private void checkQuestions(List<Question> questions) throws Exception {
		HashMap<String, Question> identifier_hm = new HashMap<String, Question>();
		HashMap<String, Question> question_hm = new HashMap<String, Question>();
		
		for (Question question : questions) {
			if (question.getQuestion().isEmpty()) {
				throw new Exception("Undefined question '"+question.getId().getValue()+"' at line: "+question.getLineNumber());
			}
			
			// Check for duplicate identifiers
			String identifier = question.getId().getValue();
			if (identifier_hm.containsKey(identifier)) {
				throw new Exception("Question '"+question.getId().getValue()+"' already declared at line: "+identifier_hm.get(identifier).getLineNumber());
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
	
	private void checkConditions(List<Expression> conditions) throws Exception {
		for (Expression condition : conditions) {
			if (condition instanceof Atom<?>) {
				condition = (Atom<?>) condition;
				System.out.println(((Atom<?>) condition).getType());
			}
			
			System.out.println(condition);
		}
	}
}
