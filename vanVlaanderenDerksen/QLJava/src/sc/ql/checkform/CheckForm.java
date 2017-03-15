package sc.ql.checkform;

import java.util.ArrayList;
import java.util.List;

import sc.ql.model.Form;

public class CheckForm {
	private final List<Message> messages;
	
	
	public CheckForm(Form form) {
		List<Message> messages = new ArrayList<Message>();
		
		CheckLabels checkLabels = new CheckLabels();
		messages.addAll(form.accept(checkLabels));
		
		this.messages = messages;

		//return messages;

		//List<FormElement> formElements = form.getFormElements();
		//List<Question> questions = createQuestionsList(formElements);

		//checkConditions(formElements, questions);
		//checkDependencies(formElements);
	}
	
	public List<Message> getMessages() {
		return this.messages;
	}
	
	/*
	 private Map<String, Question.Type> createIdTypeHashmap(List<Question> questions) {
	Map<String, Question.Type> identifier_types = new HashMap<String, Question.Type>();
		
		for (Question question : questions) {
			identifier_types.put(question.getId().getValue(), question.getType());
		}
		
		return identifier_types;
	}
	
	private void checkDependencies(List<FormElement> formElements) {
		try {
			DependenciesVisitor dependencies = new DependenciesVisitor();
			for (Node formElement : formElements) {
				formElement.accept(dependencies);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void checkConditions(List<FormElement> formElements, List<Question> questions) {
		try {
			ConditionsVisitor conditionVisitor = new ConditionsVisitor(createIdTypeHashmap(questions));
			for (Node formElement : formElements) {
				formElement.accept(conditionVisitor);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private List<Question> createQuestionsList(List<FormElement> formElements) {
		List<Question> questions = new ArrayList<Question>();
		
		for (Node formElement : formElements) {
			try {
				questions.addAll(formElement.accept(new QuestionsVisitor()));
			} catch (Exception e) {
				e.printStackTrace();
			}
        }

		return questions;
	}
	*/
}
