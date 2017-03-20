package sc.ql.checkform;

import java.util.ArrayList;
import java.util.List;

import sc.ql.model.Form;
import sc.ql.model.FormElement;
import sc.ql.model.form_elements.CalculatedQuestion;
import sc.ql.model.form_elements.IfThenElseStatement;
import sc.ql.model.form_elements.IfThenStatement;
import sc.ql.model.form_elements.Question;
import sc.ql.model.visitors.FormVisitor;
import sc.ql.model.visitors.FormElementVisitor;

public class CheckLabels implements FormVisitor<List<Message>>, FormElementVisitor<List<Message>> {
	private List<String> labels = new ArrayList<String>();
	private List<String> identifiers = new ArrayList<String>();
	private List<Message> messages = new ArrayList<Message>();
	
	@Override
	public List<Message> visit(Form form) {
		List<FormElement> elements = form.getFormElements();
		
		for(FormElement formElement : elements) {
			formElement.accept(this);
		}
		
		return messages;
	}
	
	@Override
	public List<Message> visit(IfThenStatement statement) {
		List<FormElement> thenElements = statement.getThenBody();
		
		for(FormElement formElement : thenElements) {
			formElement.accept(this);
		}

		return messages;
	}

	@Override
	public List<Message> visit(IfThenElseStatement statement) {
		List<FormElement> thenElements = statement.getThenBody();
		List<FormElement> elseElements = statement.getElseBody();
		
		for(FormElement formElement : thenElements) {
			formElement.accept(this);
		}
		
		for(FormElement formElement : elseElements) {
			formElement.accept(this);
		}
		
		return messages;
	}
	
	@Override
	public List<Message> visit(Question question) {
		String label = question.getLabel();
		String identifier = question.getId();
		
		if (labels.contains(label)) {
			messages.add(new Message(Message.MessageType.WARNING, "duplicate label detected", question.getPosition()));
		}
		else {
			labels.add(label);
		}
		
		if (identifiers.contains(identifier)) {
			messages.add(new Message(Message.MessageType.ERROR, "duplicate identifier detected", question.getPosition()));
		}
		else {
			identifiers.add(identifier);
		}
		
		return messages;
	}

	@Override
	public List<Message> visit(CalculatedQuestion question) {
		String label = question.getLabel();
		String identifier = question.getId();
		
		if (labels.contains(label)) {
			messages.add(new Message(Message.MessageType.WARNING, "duplicate label detected", question.getPosition()));
		}
		else {
			labels.add(label);
		}
		
		if (identifiers.contains(identifier)) {
			messages.add(new Message(Message.MessageType.ERROR, "duplicate identifier detected", question.getPosition()));
		}
		else {
			identifiers.add(identifier);
		}
		
		return messages;
	}

}
